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
			logLibraryVersion("com.googlecode.libphonenumber", "libphonenumber");
			logLibraryVersion("com.googlecode.libphonenumber", "geocoder");
			logLibraryVersion("com.googlecode.libphonenumber", "carrier");
		} catch (Exception e) {
			log.warn("Failed to read library versions from pom.properties", e);
			log.info("libphonenumber version: unknown");
			log.info("geocoder version: unknown");
			log.info("carrier version: unknown");
		}
	}

	private void logLibraryVersion(String groupId, String artifactId) {
		try {
			ClassPathResource resource = new ClassPathResource(
					"META-INF/maven/" + groupId + "/" + artifactId + "/pom.properties");
			if (resource.exists()) {
				InputStream inputStream = resource.getInputStream();
				Properties props = new Properties();
				props.load(inputStream);
				String version = props.getProperty("version");
				log.info("{} version: {}", artifactId, version != null ? version : "unknown");
				inputStream.close();
			} else {
				log.info("{} version: unknown", artifactId);
			}
		} catch (Exception e) {
			log.warn("Failed to read version for {}:{}", groupId, artifactId, e);
			log.info("{} version: unknown", artifactId);
		}
	}

}