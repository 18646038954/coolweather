package util;

import model.City;
import db.CoolWeatherDB;
import model.County;
import model.Province;
import android.text.TextUtils;

public class Utility {
	public synchronized static boolean handleProvincesResponse(CoolWeatherDB
			coolWeatherDB,String response){
		if(!TextUtils.isEmpty(response)){
			String[] allProvinces =response.split(",");
			if(allProvinces != null&&allProvinces.length>0){
				for(String p:allProvinces){
					String[] array =p.split("\\|");
					Province province = new Province();
					province.setProvinceName(array[0]);
					province.setProvinceCode(array[1]);
					
					coolWeatherDB.saveProvince(province);
				}
				return true;
			}
		}
		return false;
		
	}
	public static boolean handleCitiedResponse(CoolWeatherDB coolWeatherDB,String response,int provinceId){
		if(!TextUtils.isEmpty(response)){
			String[] allCities = response.split(",");
			if(allCities!=null&&allCities.length>0){
				for(String c:allCities){
					String[] array=c.split("\\|");
					City city = new City();
					city.setCityName(array[0]);
					city.setCityCode(array[1]);
					city.setProvinceId(provinceId);
					coolWeatherDB.saveCity(city);
				}
				return true;
			}
		}
		return false;
		
	}
	public static boolean handleCountiesResponse(CoolWeatherDB coolWeatherDB,String response,int cityId){
		if(!TextUtils.isEmpty(response)){
			String[]allCounties = response.split(",");
			if(allCounties !=null&&allCounties.length>0){
				for(String c:allCounties){
					String[] array=c.split("\\|");
					County county= new County();
					county.setCountyName(array[0]);
					county.setCountyCode(array[1]);
					county.setCityId(cityId);
					coolWeatherDB.saveCounty(county);
				}
				return true;
			}
			
		}
		return false;
		
	}

}











