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

	List<App> findByDelflg(String flg);

	public final String  STR_SQL = "select * from application where 1=1";
	default public List<App> appFindDetail(AppSearchModel appmo, EntityManager entyty){

		String sqlStr = makeQuery(appmo);
		Query query = entyty.createNativeQuery(sqlStr,App.class);
		List<App> apps;

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

	    apps = query.getResultList();

		return apps;
	}

	default public String makeQuery(AppSearchModel appmo) {
		StringBuffer sb = new StringBuffer(STR_SQL);

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

		String str = sb.toString();

		return str;
	}
}
