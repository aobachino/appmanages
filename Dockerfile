FROM ubuntu:18.04

ENV LANG='en_US.UTF-8' LANGUAGE='en_US:en' LC_ALL='en_US.UTF-8'

RUN apt-get update \
    && apt-get install -y --no-install-recommends curl ca-certificates fontconfig locales \
    && echo "en_US.UTF-8 UTF-8" >> /etc/locale.gen \
    && locale-gen en_US.UTF-8 \
    && rm -rf /var/lib/apt/lists/*

ENV JAVA_VERSION jdk-14.0.1+7

RUN set -eux; \
    ARCH="$(dpkg --print-architecture)"; \
    case "${ARCH}" in \
       aarch64|arm64) \
         ESUM='bf038c351337982082074ab818a6bd385c7ad31d23654a2db8cd05805fe48058'; \
         BINARY_URL='https://github.com/AdoptOpenJDK/openjdk14-binaries/releases/download/jdk-14.0.1%2B7/OpenJDK14U-jre_aarch64_linux_hotspot_14.0.1_7.tar.gz'; \
         ;; \
       armhf|armv7l) \
         ESUM='277ae66d1db7613a7f02b1cbfc9acd8f5f6208eac0e61828d40d5a513b40e406'; \
         BINARY_URL='https://github.com/AdoptOpenJDK/openjdk14-binaries/releases/download/jdk-14.0.1%2B7/OpenJDK14U-jre_arm_linux_hotspot_14.0.1_7.tar.gz'; \
         ;; \
       ppc64el|ppc64le) \
         ESUM='8af51efdd25d93b120ee9ef22e2584cf8bf9441108fa0396a0ac9c5de86ec119'; \
         BINARY_URL='https://github.com/AdoptOpenJDK/openjdk14-binaries/releases/download/jdk-14.0.1%2B7/OpenJDK14U-jre_ppc64le_linux_hotspot_14.0.1_7.tar.gz'; \
         ;; \
       s390x) \
         ESUM='97b15611f4238a20fde975cba5144477f06bb8416964ae79e2109778c25fa654'; \
         BINARY_URL='https://github.com/AdoptOpenJDK/openjdk14-binaries/releases/download/jdk-14.0.1%2B7/OpenJDK14U-jre_s390x_linux_hotspot_14.0.1_7.tar.gz'; \
         ;; \
       amd64|x86_64) \
         ESUM='3f8a4d5939a66cadf9846bbae6392644165f75063ae31998764916da13810fca'; \
         BINARY_URL='https://github.com/AdoptOpenJDK/openjdk14-binaries/releases/download/jdk-14.0.1%2B7/OpenJDK14U-jre_x64_linux_hotspot_14.0.1_7.tar.gz'; \
         ;; \
       *) \
         echo "Unsupported arch: ${ARCH}"; \
         exit 1; \
         ;; \
    esac; \
    curl -LfsSo /tmp/openjdk.tar.gz ${BINARY_URL}; \
    echo "${ESUM} */tmp/openjdk.tar.gz" | sha256sum -c -; \
    mkdir -p /opt/java/openjdk; \
    cd /opt/java/openjdk; \
    tar -xf /tmp/openjdk.tar.gz --strip-components=1; \
    rm -rf /tmp/openjdk.tar.gz;

ENV JAVA_HOME=/opt/java/openjdk \
    PATH="/opt/java/openjdk/bin:$PATH"

VOLUME /tmp
RUN mkdir /app
WORKDIR /app
# jar target
ENV JAR_TARGET "applymanage-0.0.1-SNAPSHOT.jar"
# set entrypoint to execute spring boot application
ENTRYPOINT ["sh","-c","java -jar -Dspring.profiles.active=docker target/${JAR_TARGET}"]