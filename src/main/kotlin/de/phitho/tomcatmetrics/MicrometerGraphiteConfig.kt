package de.phitho.tomcatmetrics

import io.micrometer.core.instrument.Clock
import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.core.instrument.config.NamingConvention
import io.micrometer.graphite.GraphiteConfig
import io.micrometer.graphite.GraphiteHierarchicalNameMapper
import io.micrometer.graphite.GraphiteMeterRegistry
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.net.InetAddress
import java.net.UnknownHostException


@Configuration
class MicrometerGraphiteConfig {

	@Bean
	fun hierarchicalNameMapper() =
		GraphiteHierarchicalNameMapper("replacedProgrammatically")

	@Bean
	fun graphiteMeterRegistry(config: GraphiteConfig, clock: Clock, hierarchicalNameMapper: GraphiteHierarchicalNameMapper): GraphiteMeterRegistry? {
		return GraphiteMeterRegistry(
			config,
			clock,
			{ id, convention -> hierarchicalNameMapper.toHierarchicalName(id, NamingConvention.camelCase) }
		)
	}

	@Bean
	fun commonTags(): MeterRegistryCustomizer<MeterRegistry> =
		MeterRegistryCustomizer { meterRegistry ->
			meterRegistry.config().commonTags("replacedProgrammatically", "namespace.to.my.micrometer")
		}
}
