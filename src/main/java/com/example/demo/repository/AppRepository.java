package com.example.demo.repository;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.App;
import com.example.demo.model.AppSearchModel;

//求人DBにアクセス
@Repository
public interface AppRepository  extends JpaRepository<App, Integer> {


	App findByAppId(int id);

//	List<App> findByDelflg(String flg,int limit);

	List<App> findByAppIdAndDelflg(int appId,String delflg);

	/*
	 * 求人の全件検索
	 * ※ページ番号に文字列を入れた場合の処理がない
	*/
	public final String  STR_SQL1 = "select * from application where delflg=:delflg LIMIT 20 OFFSET :page";
	default public List<App> appFind(EntityManager entyty,int pageNum){

		Query query = entyty.createNativeQuery(STR_SQL1,App.class);
		query.setParameter("delflg", "Y");
		query.setParameter("page", 20 *(pageNum - 1));
		List<App> apps;
		 apps = query.getResultList();
		return apps;
	}

	/*
	 * 求人の詳細検索
	 * ※ページ番号に文字列を入れた場合の処理がない
	 * 検索文字列が空白のみの場合の処理がない
	*/
	public final String  STR_SQL2 = "select * from application where delflg=:delflg";
	default public List<App> appFindDetail(AppSearchModel appmo, EntityManager entyty,int pageNum){

		String sqlStr = makeQuery(appmo);
		Query query = entyty.createNativeQuery(sqlStr,App.class);
		List<App> apps;
		query.setParameter("delflg", "Y");

		if(appmo.getSearch_word() != "") {
			query.setParameter("searchword", "%"+ appmo.getSearch_word() +"%");
		}

		if(appmo.getIndustry() != null) {
			query.setParameter("industryId", appmo.getIndustry());
		}

		if(appmo.getOccupation() != null) {
			query.setParameter("occupationId", appmo.getOccupation());
		}

		if(appmo.getCapital() != null) {
			query.setParameter("capital", appmo.getCapital().concat("0000"));
		}

		if(appmo.getEmployeeCnt() != null){
			query.setParameter("empcnt", appmo.getEmployeeCnt());
		}

		if(appmo.getFounding() != null) {
	        //カレンダーを生成
	        Calendar cal = Calendar.getInstance();

	        //フォーマットを設定して出力
	        SimpleDateFormat sdf = new SimpleDateFormat("y");

	        String nowAD =  sdf.format(cal.getTime());

	        int founding = Integer.parseInt(nowAD) - Integer.parseInt(appmo.getFounding());

			query.setParameter("founding", founding);
		}

		query.setParameter("page", 20 *(pageNum - 1));
	    apps = query.getResultList();

		return apps;
	}

	default public String makeQuery(AppSearchModel appmo) {
		StringBuffer sb = new StringBuffer(STR_SQL2);

		if(appmo.getSearch_word() != "") {
			sb.append(" and concat(application_name, applocation_explain,industry_name,company_name,occupation_name) like :searchword");
		}

		if(appmo.getIndustry() != null) {
			sb.append(" and industry_id = :industryId");
		}

		if(appmo.getOccupation() != null) {
			sb.append(" and occupation_id = :occupationId");
		}

		if(appmo.getCapital() != null) {
			sb.append(" and company_id in (select company_id from company where capital >= :capital)");
		}

		if(appmo.getEmployeeCnt() != null){
			sb.append(" and company_id in (select company_id from company where empcnt >= :empcnt)");
		}

		if(appmo.getFounding() != null) {
			sb.append(" and company_id in (select company_id from company where fromY >= :founding)");
		}

		sb.append(" LIMIT 20 OFFSET :page");
		String str = sb.toString();

		return str;
	}
}
