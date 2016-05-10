package com.jjm.triphelper.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.jjm.chameleon.context.ChameleonApplication;
import com.jjm.foursquare.init.FoursquareApplication;
import com.jjm.triphelper.init.Application;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.util.UriComponentsBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@IntegrationTest("server.port:0")
@WebAppConfiguration
public abstract class AbstractIntegrationTest {

    @Value("${local.server.port}")
    private int port;

    public AbstractIntegrationTest() {
        FoursquareApplication.getInstance().init("HWBMSYQA51QV0NH2I3F4GZRUWRBZAUGGFOM5PK1OXG2H1TB4", "4BAHTIQM4L22O5KOV3QN35XQ14PMF4PLRTRJXUBNN0SHFRR4");
        ChameleonApplication.getInstance().run(Application.class);
    }

    private String getBaseUrl() {
        return "http://localhost:" + port;
    }

    protected <T> ResponseEntity<T> getEntity(final String requestMappingUrl, final Class<T> serviceReturnTypeClass, final Map<String, Object> parametersInOrderOfAppearance) {
        final TestRestTemplate restTemplate = new TestRestTemplate();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(getBaseUrl() + requestMappingUrl);
        final Iterator it = parametersInOrderOfAppearance.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            builder.queryParam((String) pair.getKey(), pair.getValue());
        }
        return restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity, serviceReturnTypeClass);
    }

    protected <T> List<T> getList(final String requestMappingUrl, final Class<T> serviceListReturnTypeClass, final Object... parametersInOrderOfAppearance) {
        final ObjectMapper mapper = new ObjectMapper();
        final TestRestTemplate restTemplate = new TestRestTemplate();
        final HttpEntity<String> requestEntity = new HttpEntity<String>(new HttpHeaders());
        try {
            // Retrieve list
            final ResponseEntity<List> entity = restTemplate.exchange(getBaseUrl() + requestMappingUrl, HttpMethod.GET, requestEntity, List.class, parametersInOrderOfAppearance);
            final List<Map<String, String>> entries = entity.getBody();
            final List<T> returnList = new ArrayList<T>();
            for (final Map<String, String> entry : entries) {
                // Fill return list with converted objects
                returnList.add(mapper.convertValue(entry, serviceListReturnTypeClass));
            }
            return returnList;
        } catch (final Exception ex) {
            // Handle exceptions
        }
        return null;
    }

    protected <T> T postEntity(final String requestMappingUrl, final Class<T> serviceReturnTypeClass, final Object objectToPost) {
        final TestRestTemplate restTemplate = new TestRestTemplate();
        final ObjectMapper mapper = new ObjectMapper();
        try {
            final HttpEntity<String> requestEntity = new HttpEntity<String>(mapper.writeValueAsString(objectToPost));
            final ResponseEntity<T> entity = restTemplate.postForEntity(getBaseUrl() + requestMappingUrl, requestEntity, serviceReturnTypeClass);
            return entity.getBody();
        } catch (final Exception ex) {
            // Handle exceptions
        }
        return null;
    }
}