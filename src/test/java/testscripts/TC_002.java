package testscripts;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class TC_002 {



  @Test(priority = 1)
    public void getAllVideos() {

        Response resp = given().contentType(ContentType.JSON).when().
                log().all().
                get("http://localhost:8010/app/videogames");

        System.out.println(resp.prettyPrint());





    }

    /**
   * Add a new video game to the DB
   */

  @Test(priority = 2)
  public void addNewVideoGames(){

    Integer i = (int) (Math.random()*2822);
    String id = i.toString();

    HashMap data = new HashMap();
    data.put("id",id);
    data.put( "name", "Spider man");
    data.put( "releaseDate", "2022-06-18T07:44:30.213Z");
    data.put( "reviewScore", "5");
    data.put( "category", "Adventure");
    data.put( "rating", "Universal");


   Response resp =  given().contentType(ContentType.JSON).log().all()
            .body(data).when().
            post("http://localhost:8010/app/videogames").then().statusCode(200)
            .log().body().extract().response();

      String header = resp.header("content-type");
      System.out.println("Header: "+ header);
    Assert.assertEquals(header.contains("application/xml"),true,"Message not found");


    System.out.println(resp.prettyPrint());
      String jsonStr =   resp.asString();
    Assert.assertEquals(jsonStr.contains("Record Added Successfully"),true,"Message not found");


    }




}
