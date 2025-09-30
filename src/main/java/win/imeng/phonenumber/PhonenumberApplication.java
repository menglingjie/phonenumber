package win.imeng.phonenumber;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.util.Properties;

@SpringBootApplication
@Slf4j
public class PhonenumberApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhonenumberApplication.class, args);
	}

	@PostConstruct
	public void logLibraryVersions() {
		try {
			// 通过读取 META-INF/maven 目录下的 pom.properties 文件获取版本信息
			ClassPathResource resource = new ClassPathResource("META-INF/maven/com.googlecode.libphonenumber/libphonenumber/pom.properties");
			if (resource.exists()) {
				InputStream inputStream = resource.getInputStream();
				Properties props = new Properties();
				props.load(inputStream);
				String version = props.getProperty("version");
				log.info("libphonenumber version: {}", version != null ? version : "unknown");
				inputStream.close();
			} else {
				log.info("libphonenumber version: unknown");
			}

			resource = new ClassPathResource("META-INF/maven/com.googlecode.libphonenumber/geocoder/pom.properties");
			if (resource.exists()) {
				InputStream inputStream = resource.getInputStream();
				Properties props = new Properties();
				props.load(inputStream);
				String version = props.getProperty("version");
				log.info("geocoder version: {}", version != null ? version : "unknown");
				inputStream.close();
			} else {
				log.info("geocoder version: unknown");
			}

			resource = new ClassPathResource("META-INF/maven/com.googlecode.libphonenumber/carrier/pom.properties");
			if (resource.exists()) {
				InputStream inputStream = resource.getInputStream();
				Properties props = new Properties();
				props.load(inputStream);
				String version = props.getProperty("version");
				log.info("carrier version: {}", version != null ? version : "unknown");
				inputStream.close();
			} else {
				log.info("carrier version: unknown");
			}
		} catch (Exception e) {
			log.warn("Failed to read library versions from pom.properties", e);
			log.info("libphonenumber version: unknown");
			log.info("geocoder version: unknown");
			log.info("carrier version: unknown");
		}
	}

}