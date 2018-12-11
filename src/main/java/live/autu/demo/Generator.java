package live.autu.demo;

import javax.sql.DataSource;

import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;

import live.autu.generator.GeneratorApplication;
import live.autu.generator.config.Config;
import live.autu.generator.core.MetaBuilder;
import live.autu.generator.generator.ssm.ApiGenerator;
import live.autu.generator.generator.ssm.DaoGenerator;
import live.autu.generator.generator.ssm.MapperGenerator;
import live.autu.generator.generator.ssm.ModelGenerator;
import live.autu.generator.generator.ssm.ServiceGenerator;

public class Generator {
	public static void main(String[] args) {
		   
			// model 所使用的包名 
			String modelPackageName = "live.autu.demo.model";
			// dao 所使用的包名 
			String daoPackageName = "live.autu.demo.dao";
			// service 所使用的包名 
			String servicePackageName = "live.autu.demo.service";
			// api 所使用的包名 
			String apiPackageName = "live.autu.demo.api";
			// model 文件保存路径 
			String modelOutputDir = PathKit.getWebRootPath()
					+ "/src/main/java/live/autu/demo/model";
			// dao 文件保存路径 
			String daoOutputDir = PathKit.getWebRootPath()
					+ "/src/main/java/live/autu/demo/dao";
			// mapper 文件保存路径 
			String mapperOutputDir= PathKit.getWebRootPath()
					+ "/src/main/java/live/autu/demo/mapper";
			// service 文件保存路径 
			String serviceOutputDir= PathKit.getWebRootPath()
							+ "/src/main/java/live/autu/demo/service";
			// api 文件保存路径 
			String apiOutputDir= PathKit.getWebRootPath()
										+ "/src/main/java/live/autu/demo/api";
		 
			// 创建生成器
			GeneratorApplication application = new GeneratorApplication();
			// 设置数据库方言
			application.setDialect(new MysqlDialect());
			
			application
			//添加Model生成器
			.addGenerator(
				new ModelGenerator()
					.setPackageName(modelPackageName)
					.setGenerateChainSetter(true)
					.setOutputDir(modelOutputDir)
			)
			//添加Mapper生成器
			.addGenerator(
				new MapperGenerator()
					.setOutputDir(mapperOutputDir)
					).addGenerator(
					new DaoGenerator()
					.setModelPackageName(modelPackageName)
					.setDaoPackageName(daoPackageName)
					.setOutputDir(daoOutputDir)
			)
			//添加Service生成器
			.addGenerator(new ServiceGenerator()
					.setServicePackageName(servicePackageName)
					.setDaoPackageName(daoPackageName)
					.setModelPackageName(modelPackageName)
					.setOutputDir(serviceOutputDir))
			//添加API生成器
			.addGenerator(new ApiGenerator()
					.setApiPackageName(apiPackageName)
					.setServicePackageName(servicePackageName)
					.setModelPackageName(modelPackageName)
					.setOutputDir(apiOutputDir)
					);

			application.setMetaBuilder(new MetaBuilder());
			application.generate();
	}
}
