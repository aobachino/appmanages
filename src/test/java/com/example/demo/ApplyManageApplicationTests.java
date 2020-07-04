package com.example.demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.demo.domain.App;
import com.example.demo.repository.AppRepository;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.IndustryRepository;
import com.example.demo.repository.OccupationRepository;
import com.example.demo.service.OfferService;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ApplyManageApplicationTests {


	public ApplyManageApplicationTests() {
		super();
	}

	@Autowired
	private AppRepository appRepository;

	@Autowired
	private OfferService offerservice;

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private IndustryRepository industryRepository;

	@Autowired
	private OccupationRepository occupationRepository;


	//排他処理テスト()
	@Test
	public void offerInsertTest() {

		System.out.println("-------スレッド作成---------------------------------");
		int threadNum = 2;
		ExecutorService threadpool = Executors.newFixedThreadPool(threadNum);
		System.out.println(threadpool);
		System.out.println("-------" + threadNum + "スレッド作成完了---------------------------------");

		System.out.println("-------ジョブ作成---------------------------------");
		Collection<Callable<Integer>> jobs = new ArrayList<Callable<Integer>>();
		System.out.println(jobs);
		System.out.println("------------------------------------------------------");

		// 結果や中間結果を入れる箱
		List<Future<Integer>> futures;
		List<Integer> results = new ArrayList<Integer>();

		//コールメソッドを無名関数で実装
		for(int i = 0; i < threadNum; i++){
			System.out.println("-------コールメソッド追加---------------------------------");
			jobs.add(new Callable<Integer>(){
				@Override
				public Integer call() throws Exception {
					//これはスレッドセーフな必要あり
					return offerservice.offerInsert(1, "user1");
				}

			});
			System.out.println(jobs);
			System.out.println("----------------------------------------------------------");
		}

		try{

			System.out.println("-------スレッド実行---------------------------------");
			futures = threadpool.invokeAll(jobs); //スレッドの実行
			System.out.println(futures);
			System.out.println("--------------------------------------------------------");


			for(int i = 0; i < threadNum; i++){
				//結果の取得
				results.add(futures.get(i).get());
			}
			for(Integer inum: results){

				System.out.println("-------結果の表示---------------------------------");
				System.out.println(inum);
				System.out.println("------------------------------------------------");
			}

			//例外処理
		}catch(InterruptedException | ExecutionException e){
			System.out.println("-------例外処理---------------------------------");
			e.printStackTrace();
			System.out.println("----------------------------------------------------");

			//絶対実行したい
		}finally {

			System.out.println("-------最後にシャットダウン---------------------------------");
			threadpool.shutdown();
			System.out.println("------------------------------------------------------------");
		}

	}

	//負荷処理テスト
	@Test
	public void loadHandling() {

		System.out.println("-------データインサート------------------------------------------");
		Random random = new Random();
		for(int i = 1001;i < 50000;i++) {
			int indsNum = random.nextInt(4)+1;
			int compaNum = random.nextInt(4)+1;
			int occupNum = random.nextInt(4)+1;
			App app = new App(i,"テスト","テストテストテストテスト",indsNum,industryRepository.findByIndustryId(indsNum).get(0).getIndustryName(),compaNum,companyRepository.findByCompanyId(compaNum).get(0).getCompanyName(),occupNum,occupationRepository.findByOccupationId(occupNum).get(0).getOccupationName(),"99999999","99999999","Y",10);
			app = appRepository.save(app);
		}
		System.out.println("-----------------------------------------------------------------");
	}
}

