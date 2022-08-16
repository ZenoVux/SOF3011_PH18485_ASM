package utility;

//import org.hibernate.dialect.sqls;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import model.Tivi;
import model.Account;
import model.Brand;
import model.Cart;
import model.CartDetail;
import model.OperatingSystem;
import model.Resolution;
import model.ScreenType;

public class HibernateUtil {

	private static final SessionFactory FACTORY;

    static {
        Configuration conf = new Configuration();

        // Cấu hình kết nối
        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.SQLServer2016Dialect");
        properties.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        properties.put(Environment.URL, "jdbc:sqlserver://localhost:1433;databaseName=SOF3011_ASM;encrypt=true;trustServerCertificate=true;");
        properties.put(Environment.USER, "sa");
        properties.put(Environment.PASS, "07022002");
        properties.put(Environment.SHOW_SQL, "true");
        
        // Tự động tạo Table
        properties.put(Environment.HBM2DDL_AUTO, "update");
        
        conf.setProperties(properties);
        
        // Cấu hình ORM
        // Khai báo class đc ánh xạ
        conf.addAnnotatedClass(OperatingSystem.class);
        conf.addAnnotatedClass(Brand.class);
        conf.addAnnotatedClass(ScreenType.class);
        conf.addAnnotatedClass(Resolution.class);
        conf.addAnnotatedClass(Account.class);
        conf.addAnnotatedClass(Tivi.class);
        conf.addAnnotatedClass(Cart.class);
        conf.addAnnotatedClass(CartDetail.class);

        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties()).build();
        FACTORY = conf.buildSessionFactory(registry);

    }

    public static SessionFactory getFACTORY() {
        return FACTORY;
    }
}
