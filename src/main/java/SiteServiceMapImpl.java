import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class SiteServiceMapImpl implements ISiteService  {
    private Map<String , Site> siteMap;
    private Map<String, Category> categoriesMap;

    public SiteServiceMapImpl()  {
        siteMap = new HashMap<String, Site>();
        categoriesMap = new HashMap<String,Category>();
        }


    // Funciones http  ----------------------------------------

    public Collection<Site> getSites()
    {

        try {
            URL urlSites = new URL("https://api.mercadolibre.com/sites");
            try {
                URLConnection urlSitesConnection = urlSites.openConnection();
                urlSitesConnection.setRequestProperty("Accept","application/json");
                urlSitesConnection.setRequestProperty("User-agent","Mozilla/5.0");

                if(urlSitesConnection instanceof HttpURLConnection){
                    HttpURLConnection connectionSites = (HttpURLConnection)urlSitesConnection;
                    BufferedReader in = new BufferedReader(new InputStreamReader(connectionSites.getInputStream()));

                    Gson gson = new Gson();
                    Site[] sitesArray = gson.fromJson(in, Site[].class);
                    in.close();


                   // en este for metemos los sites al hashmap
                    for(int i=0 ; i<sitesArray.length ; i++){
                        System.out.println("Site agregado al hashMap:");
                        System.out.println(sitesArray[i].toString());
                        siteMap.put(sitesArray[i].getId(), sitesArray[i]);

                }

                in.close();

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return siteMap.values();
    }




    public Collection<Category> getCategories(String id) {

        categoriesMap.clear(); //Este comando limpia el map para llamados consecutivos con distintos id

        try{
            URL urlCategories = new URL("https://api.mercadolibre.com/sites/"+ id +"/categories");
            try{
                URLConnection urlCategoriesConnection = urlCategories.openConnection();
                urlCategoriesConnection.setRequestProperty("Accept","application/json");
                urlCategoriesConnection.setRequestProperty("User-agent","Mozilla/5.0");

                if(urlCategoriesConnection instanceof HttpURLConnection){
                    HttpURLConnection connectionCategories = (HttpURLConnection)urlCategoriesConnection;
                    BufferedReader in2 = new BufferedReader(new InputStreamReader(connectionCategories.getInputStream()));

                    Gson gson2 = new Gson();

                    Category[] categoriesArray = gson2.fromJson(in2, Category[].class);

                    //En este for metemos las categories en el hasMap
                    for(int i=0 ; i<categoriesArray.length ; i++){
                        //categoryList.add(categoriesArray[i]);
                        categoriesMap.put((categoriesArray[i].getId()),categoriesArray[i]);
                        System.out.println(categoriesArray[i].getId());
                    }
                    in2.close();


                    return categoriesMap.values();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
        //return categoriesMap.values();
    }
}
