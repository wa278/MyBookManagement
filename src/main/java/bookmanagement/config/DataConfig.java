package bookmanagement.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import javafx.scene.chart.PieChart;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import javax.sql.DataSource;

@Configuration
@ImportResource("classpath:Spring-dao.xml")
public class DataConfig{


}
