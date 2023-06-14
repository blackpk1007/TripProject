package com.trip.project.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trip.project.dto.PlaceDTO;
import com.trip.project.mapper.PlaceMapper;

@Service
public class CrawlingServiceImpl implements CrawlingService {

	@Autowired
	private PlaceMapper pmapper;

	@Override
	public int crawinsert() {
		
		// ChromeDriver 실행 파일의 경로 설정
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
		// ChromeDriver의 인스턴스 생성
		ChromeOptions options = new ChromeOptions();
		options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		options.addArguments("--start-maximized");
		options.addArguments("--disable-popup-blocking");
		options.addArguments("--remote-allow-origins=*");

		WebDriver driver = new ChromeDriver(options);
		try {
			// 네이버 지도 검색 페이지로 이동
			driver.get(
					"https://www.visitjeju.net/kr/detail/list?menuId=DOM_000001718000000000&cate1cd=cate0000000002#p82&pageSize=15&sortListType=reviewcnt&viewType=thumb&isShowBtag&tag");

			Thread.sleep(3000);
			// 맛집 목록이 로드될 때까지 기다립니다.
			int page = 82	;

			while (true) {

				System.out.println("Page: " + page+"\t");

				for (int i = 1; i <= 15; i++) {
					System.out.println(i+"번째");
					WebElement category = driver
							.findElement(By.xpath("/html/body/div/div[2]/div/div[2]/div/div/div/div[2]/div[5]/ul/li["
									+ i + "]/dl/dt/a/span"));
					if (category.getText().equals("관광지")) {
						Thread.sleep(3000);
						WebElement element = driver.findElement(By.xpath(
								"/html/body/div/div[2]/div/div[2]/div/div/div/div[2]/div[5]/ul/li[" + i + "]/dl/dt/a"));
						WebElement title = driver.findElement(
								By.xpath("/html/body/div/div[2]/div/div[2]/div/div/div/div[2]/div[5]/ul/li[" + i
										+ "]/dl/dt/a/p[1]"));
						
						String title1 = title.getText().replaceAll("[^ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9,. ()-]", "");
						
						WebElement img = driver.findElement(
								By.xpath("/html/body/div/div[2]/div/div[2]/div/div/div/div[2]/div[5]/ul/li[" + i
										+ "]/dl/dt/a/img"));

						extractAndSaveImages(driver, title1, img);

						element.click();

						Thread.sleep(3000);

						// WebElement detail =
						// driver.findElement(By.xpath("/html/body/div/div[2]/div/div[2]/div/div/div/div[2]/div[5]/div[2]/div/div/div/div[1]/div[1]/div/div[1]/div[3]/div[3]/div/div/div[1]/div/div/a"));

						// detail.click();
						// 새 탭으로 전환
						// List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());

						// Switch to the new tab
						// driver.switchTo().window(windowHandles.get(windowHandles.size() - 1));
						// 맛집 정보 출력

						pmapper.crawinsert(printRestaurantInfo(driver, title1));

						driver.navigate().back();
						// 새 탭 닫기
						// driver.close();

						// driver.switchTo().window(windowHandles.get(0));
					}
				}
				WebElement nextPageButton = driver.findElement(
						By.xpath("/html/body/div/div[2]/div/div[2]/div/div/div/div[2]/div[5]/div[2]/div/a[8]"));
				if (!nextPageButton.isDisplayed()) {
					break;
				}
				nextPageButton.click();
				page++;
			}

		} catch (Exception e) {
			System.out.println("에러 발생");
			e.printStackTrace();
		} finally {
			// WebDriver 종료
			driver.quit();
		}
		
		return 0;
	}

	private static PlaceDTO printRestaurantInfo(WebDriver driver, String title1) throws InterruptedException { // 맛집 목록 가져오기
		Thread.sleep(5000);

		String clientId = "562aepgcmq"; // 본인이 발급받은 클라이언트 ID
		String clientSecret = "mHqZJ6WN87FwWvsPfOuXsPPmsyWFAN0fvAbp4G99"; // 본인이 발급받은 클라이언트 시크릿

		WebElement addr = null;
		WebElement phone = null;
		WebElement good = null;
		WebElement content = null;
		WebElement detailcontent = null;
		WebElement tag1 = null;
		WebElement tag2 = null;
		WebElement tag3 = null;
		HttpResponse response = null;
		HttpEntity entity = null;
		String responseBody = null;
		JSONObject address = null;
		String x = null;
		String y = null;

		JSONObject json = null;
		JSONArray addresses = null;

		/*
		 * try { By titleLocator = By .xpath(
		 * "/html/body/div/div[2]/div/div[2]/div[2]/div/div/div[2]/div[1]/div[1]/div[2]/div[1]/h3"
		 * ); title = driver.findElement(titleLocator); } catch (Exception e) { title =
		 * null; }
		 */

		try {
			By addrLocator = By.xpath(
					"/html/body/div/div[2]/div/div[2]/div[2]/div/div/div[2]/div[1]/div[1]/div[2]/div[5]/div[1]/p[2]");
			addr = driver.findElement(addrLocator);
			String query = addr.getText();
			HttpClient httpClient = HttpClients.createDefault();
			String encodedQuery = URLEncoder.encode(query, "UTF-8");
			String url = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=" + encodedQuery;

			HttpGet request = new HttpGet(url);
			request.setHeader("X-NCP-APIGW-API-KEY-ID", clientId);
			request.setHeader("X-NCP-APIGW-API-KEY", clientSecret);

			response = httpClient.execute(request);
			entity = response.getEntity();
			responseBody = EntityUtils.toString(entity);

			json = new JSONObject(responseBody);
			addresses = json.getJSONArray("addresses");
			if (addresses.length() > 0) {
				address = addresses.getJSONObject(0);
				x = address.getString("x"); // x 좌표
				y = address.getString("y"); // y 좌표
			} else {
				System.out.println("No addresses found.");
			}
		} catch (Exception e) {
			addr = null;
		}

		try {
			By phoneLocator = By.xpath(
					"/html/body/div/div[2]/div/div[2]/div[2]/div/div/div[2]/div[1]/div[1]/div[2]/div[5]/div[2]/p[2]");
			phone = driver.findElement(phoneLocator);
		} catch (Exception e) {
			phone = null;
		}

		try {
			By goodLocator = By
					.xpath("/html/body/div/div[2]/div/div[2]/div[2]/div/div/div[2]/div[2]/ul/li[2]/button/p[2]");
			good = driver.findElement(goodLocator);
		} catch (Exception e) {
			good = null;
		}

		try {
			By contentLocator = By.xpath(
					"/html/body/div/div[2]/div/div[2]/div[2]/div/div/div[2]/div[2]/div[2]/div[2]/div[2]/div/dl/dd[1]");
			content = driver.findElement(contentLocator);
		} catch (Exception e) {
			content = null;
		}

		try {
			By detailContentLocator = By.xpath(
					"/html/body/div/div[2]/div/div[2]/div[2]/div/div/div[2]/div[2]/div[2]/div[2]/div[2]/div/dl/dd[2]");
			detailcontent = driver.findElement(detailContentLocator);
		} catch (Exception e) {
			detailcontent = null;
		}

		try {
			By tagLocator1 = By.xpath(
					"/html/body/div/div[2]/div/div[2]/div[2]/div/div/div[2]/div[1]/div[1]/div[2]/div[4]/p[1]/a[1]");
			tag1 = driver.findElement(tagLocator1);
		} catch (Exception e) {
			tag1 = null;
		}
		try {
			By tagLocator2 = By.xpath(
					"/html/body/div/div[2]/div/div[2]/div[2]/div/div/div[2]/div[1]/div[1]/div[2]/div[4]/p[1]/a[2]");
			tag2 = driver.findElement(tagLocator2);
		} catch (Exception e) {
			tag2 = null;
		}
		try {
			By tagLocator3 = By.xpath(
					"/html/body/div/div[2]/div/div[2]/div[2]/div/div/div[2]/div[1]/div[1]/div[2]/div[4]/p[1]/a[3]");
			tag3 = driver.findElement(tagLocator3);
		} catch (Exception e) {
			tag3 = null;
		}
		
		PlaceDTO pdto = new PlaceDTO();
		pdto.setPlaceName(title1);
		pdto.setPlaceAddress((addr != null ? addr.getText() : ""));
		pdto.setPlaceCategory("관광지");
		pdto.setPlacePhone((phone != null ? phone.getText() : ""));
		pdto.setPlaceGood(Integer.parseInt(good != null ? good.getText().replaceAll(",", "") : ""));
		pdto.setPlaceContent((content != null ? content.getText() : ""));
		pdto.setPlaceInfo((detailcontent != null ? detailcontent.getText() : ""));
		pdto.setPlaceTag1((tag1 != null ? tag1.getText() : ""));
		pdto.setPlaceTag2((tag2 != null ? tag2.getText() : ""));
		pdto.setPlaceTag3((tag3 != null ? tag3.getText() : ""));
		pdto.setPlaceLat((x != null ? x : ""));
		pdto.setPlaceLon((y != null ? y : ""));
		System.out.println("x : "+x);
		System.out.println("y : "+y);
		System.out.println("title : "+title1);
		return pdto;
		/*
		 * restaurantObject.put("placeName", title.getText() != null ?
		 * title.getText().replaceAll("[^ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9,. ()]", "") : "");
		 * restaurantObject.put("placeAddress", (addr != null ? addr.getText() : ""));
		 * restaurantObject.put("placeCategory", "음식점");
		 * restaurantObject.put("placePhone", (phone != null ? phone.getText() : ""));
		 * restaurantObject.put("placeGood", (good != null ? good.getText() : ""));
		 * restaurantObject.put("placeContent", (content != null ? content.getText() :
		 * "")); restaurantObject.put("placeInfo", (detailcontent != null ?
		 * detailcontent.getText() : "")); restaurantObject.put("placeTag1", (tag1 !=
		 * null ? tag1.getText() : "")); restaurantObject.put("placeTag2", (tag2 != null
		 * ? tag2.getText() : "")); restaurantObject.put("placeTag3", (tag3 != null ?
		 * tag3.getText() : "")); restaurantObject.put("placeLon", (y != null ? y :
		 * "")); restaurantObject.put("placeLat", (x != null ? x : ""));
		 */

	}

	private static void extractAndSaveImages(WebDriver driver, String title1, WebElement img)
			throws InterruptedException {
		Thread.sleep(3000);
		String imageUrl = img.getAttribute("src");
		if (!imageUrl.isEmpty()) {
			try {

				Path targetPath = Paths
						.get("C:\\image\\place\\" +title1+ ".jpg");
				if (Files.exists(targetPath)) {
					System.out.println("Image already exists: " + targetPath);
					return;
				}
				URL imageURL = new URL(imageUrl);
				try (InputStream inputStream = imageURL.openStream()) {
					Files.copy(inputStream, targetPath, StandardCopyOption.REPLACE_EXISTING);
					System.out.println("Image saved: " + targetPath);
				}
			} catch (IOException e) {
				System.out.println("Failed to save image: " + imageUrl);
			}
		}
	}
}
