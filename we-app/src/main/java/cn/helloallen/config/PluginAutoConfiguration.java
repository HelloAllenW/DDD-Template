package cn.helloallen.config;
import cn.helloallen.plugin.FieldEncryptionAndDecryptionMybatisPlugin;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PluginAutoConfiguration {

    @Bean
    public Interceptor plugin() {
        return new FieldEncryptionAndDecryptionMybatisPlugin();
    }

}
