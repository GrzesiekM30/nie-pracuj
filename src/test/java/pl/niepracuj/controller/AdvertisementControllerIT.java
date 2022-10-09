package pl.niepracuj.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import pl.niepracuj.model.dto.advertisement.AdvertisementSearchCriteriaDto;
import pl.niepracuj.model.enums.SeniorityEnum;
import pl.niepracuj.model.enums.TechnologyEnum;

import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.niepracuj.util.TestUtils.toJson;

@SpringBootTest
@AutoConfigureMockMvc
public class AdvertisementControllerIT {

    @Autowired
    private MockMvc mockMvc;

//    @Test
//    @Sql("/sql/controller/advertisement.sql")
//    public void whenGetAllAdvertisements_thenReturnAdvertisements() throws Exception {
//        // when && then
//        mockMvc.perform(get("/adv/all")).andExpect(status().isOk())
//                .andExpect(jsonPath("$.size()", Matchers.equalTo(1)));
//    }

//    @Test
//    @Sql("/sql/controller/advertisement.sql")
//    public void whenGetAdvertisementsByCriteria_ThenOkResponse () throws Exception {
//
//        var criteria = AdvertisementSearchCriteriaDto.builder()
//                .technologyName(TechnologyEnum.JAVA).build();
//        var criteriaJson = toJson(criteria);
//        // when && then
//        mockMvc.perform(post("/adv/search")
//                        .content(criteriaJson)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.size()", Matchers.equalTo(2)));
//    }
    //Test parametryzowany
    //1 technology: JAVA -> 2
    //2 city: Nowogrodziec -> 1
    //3 seniority: MID -> 1

    @ParameterizedTest
    @ArgumentsSource(CriteriaProvider.class)
    @Sql("/sql/controller/advertisement.sql")
    public void whenGetAdvertisementsByCriteriaParametrized_ThenOkResponse (TechnologyEnum technology,
                                                                            String city,
                                                                            SeniorityEnum seniority,
                                                                            int result) throws Exception {

        var criteria = AdvertisementSearchCriteriaDto.builder()
                .technologyName(technology)
                .cityName(city)
                .seniorityName(seniority)
                .build();
        var criteriaJson = toJson(criteria);
        // when && then
        mockMvc.perform(post("/adv/search")
                        .content(criteriaJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", Matchers.equalTo(result)));
    }

    static class CriteriaProvider implements ArgumentsProvider{

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
            return Stream.of(
                    Arguments.of(TechnologyEnum.JAVA, null, null, 2),
                    Arguments.of(null, null, SeniorityEnum.MID, 1),
                    Arguments.of(null, 'Nowogrodziec', null, 1));
        }
    }
}