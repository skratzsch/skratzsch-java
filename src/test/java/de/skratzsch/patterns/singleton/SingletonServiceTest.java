package de.skratzsch.patterns.singleton;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

class SingletonServiceTest {

    @Test
    public void website200() {
        SingletonService service = SingletonService.getInstance();
        service.fetchDataFromUrl("https://www.google.com");
    }

    @Test
    public void websiteMoved() {
        SingletonService service = SingletonService.getInstance();
        String response = service.fetchDataFromUrl("https://google.com");

        assertThat(response, containsString("<TITLE>301 Moved</TITLE>"));
    }
}