package controller;

import com.dreamjob.controller.VacancyController;
import com.dreamjob.model.City;
import com.dreamjob.model.FileDto;
import com.dreamjob.model.Vacancy;
import com.dreamjob.service.CityService;
import com.dreamjob.service.VacancyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.ui.ConcurrentModel;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Optional;

import static java.time.LocalDateTime.now;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

public class VacancyControllerTest {

    private VacancyService vacancyService;

    private CityService cityService;

    private VacancyController vacancyController;

    private MultipartFile testFile;

    @BeforeEach
    public void initServices() {
        vacancyService = mock(VacancyService.class);
        cityService = mock(CityService.class);
        vacancyController = new VacancyController(vacancyService, cityService);
        testFile = new MockMultipartFile("testFile.img", new byte[] {1, 2, 3});
    }

    @Test
    public void whenRequestVacancyListPageThenGetPageWithVacancies() {
        var vacancy1 = new Vacancy(1, "test1", "desc1",
                now(), true, 1, 2);
        var vacancy2 = new Vacancy(2, "test2", "desc2",
                now(), false, 3, 4);
        var expectedVacancies = List.of(vacancy1, vacancy2);
        when(vacancyService.findAll()).thenReturn(expectedVacancies);
        var model = new ConcurrentModel();
        var view = vacancyController.getAll(model);
        var actualVacancies = model.getAttribute("vacancies");
        assertThat(view).isEqualTo("vacancies/list");
        assertThat(actualVacancies).isEqualTo(expectedVacancies);
    }

    @Test
    public void whenRequestVacancyCreationPageThenGetPageWithCities() {
        var city1 = new City(1, "Москва");
        var city2 = new City(2, "Санкт-Петербург");
        var expectedCities = List.of(city1, city2);
        when(cityService.findAll()).thenReturn(expectedCities);
        var model = new ConcurrentModel();
        var view = vacancyController.getCreationPage(model);
        var actualVacancies = model.getAttribute("cities");
        assertThat(view).isEqualTo("vacancies/create");
        assertThat(actualVacancies).isEqualTo(expectedCities);
    }

    @Test
    public void whenRequestCreationPageThenGetPage() {
        var model = new ConcurrentModel();
        var view = vacancyController.getAll(model);
        assertThat(view).isEqualTo("vacancies/list");
    }

    @Test
    public void whenPostVacancyWithFileThenSameDataAndRedirectToVacanciesPage() throws Exception {
        var vacancy = new Vacancy(1, "test1", "desc1", now(), true, 1, 2);
        var fileDto = new FileDto(testFile.getOriginalFilename(), testFile.getBytes());
        var vacancyArgumentCaptor = ArgumentCaptor.forClass(Vacancy.class);
        var fileDtoArgumentCaptor = ArgumentCaptor.forClass(FileDto.class);
        when(vacancyService.save(vacancyArgumentCaptor.capture(), fileDtoArgumentCaptor.capture())).thenReturn(vacancy);
        var model = new ConcurrentModel();
        var view = vacancyController.create(vacancy, testFile, model);
        var actualVacancy = vacancyArgumentCaptor.getValue();
        var actualFileDto = fileDtoArgumentCaptor.getValue();
        assertThat(view).isEqualTo("redirect:/vacancies");
        assertThat(actualVacancy).isEqualTo(vacancy);
        assertThat(fileDto).usingRecursiveComparison().isEqualTo(actualFileDto);

    }

    @Test
    public void whenPostVacancyWithFileThenGetError() {
        var expectedException = new RuntimeException("Failed to write file");
        when(vacancyService.save(any(), any())).thenThrow(expectedException);
        var model = new ConcurrentModel();
        var view = vacancyController.create(new Vacancy(), testFile, model);
        var actualExceptionMessage = model.getAttribute("message");
        assertThat(view).isEqualTo("errors/404");
        assertThat(actualExceptionMessage).isEqualTo(expectedException.getMessage());
    }

    @Test
    public void whenRequestVacancyByIdThenGetCorrespondingVacancy() {
        var vacancy = new Vacancy(
                1, "test1", "desc1", now(), true, 1, 2);
        when(vacancyService.findById(vacancy.getId())).thenReturn(Optional.of(vacancy));
        var model = new ConcurrentModel();
        var view = vacancyController.getById(model, vacancy.getId());
        var actualVacancy = model.getAttribute("vacancy");
        assertThat(view).isEqualTo("vacancies/one");
        assertThat(actualVacancy).isEqualTo(vacancy);
    }

    @Test
    public void whenRequestVacancyByNotExistingIdThenGetError() {
        when(vacancyService.findById(1)).thenReturn(Optional.empty());
        var model = new ConcurrentModel();
        var view = vacancyController.getById(model, 1);
        var actualMessage = model.getAttribute("message");
        assertThat(view).isEqualTo("errors/404");
        assertThat(actualMessage).isEqualTo("Вакансия с указанным идентификатором не найдена");
    }

    @Test
    public void whenUpdateVacancyWithFileThenSameDataAndRedirectToVacanciesPage() throws Exception {
        var vacancy = new Vacancy(1, "test1", "desc1",
                now(), true, 1, 2);
        var fileDto = new FileDto(testFile.getOriginalFilename(), testFile.getBytes());
        var vacancyArgumentCaptor = ArgumentCaptor.forClass(Vacancy.class);
        var fileDtoArgumentCaptor = ArgumentCaptor.forClass(FileDto.class);
        when(vacancyService.update(vacancyArgumentCaptor.capture(),
                fileDtoArgumentCaptor.capture())).thenReturn(true);
        var model = new ConcurrentModel();
        var view = vacancyController.update(vacancy, testFile, model);
        var actualVacancy = vacancyArgumentCaptor.getValue();
        var actualFileDto = fileDtoArgumentCaptor.getValue();
        assertThat(view).isEqualTo("redirect:/vacancies");
        assertThat(actualVacancy).isEqualTo(vacancy);
        assertThat(fileDto).usingRecursiveComparison().isEqualTo(actualFileDto);
    }

    @Test
    public void whenUpdateVacancyWithFileThenGetError() {
        var expectedException = new RuntimeException("Failed to write file");
        when(vacancyService.update(any(), any())).thenThrow(expectedException);
        var model = new ConcurrentModel();
        var view = vacancyController.update(new Vacancy(), testFile, model);
        var actualExceptionMessage = model.getAttribute("message");
        assertThat(view).isEqualTo("errors/404");
        assertThat(actualExceptionMessage).isEqualTo(expectedException.getMessage());
    }

    @Test
    public void whenDeleteVacancyThenGetPage() {
        when(vacancyService.deleteById(1)).thenReturn(true);
        var model = new ConcurrentModel();
        var view = vacancyController.delete(model, 1);
        assertThat(view).isEqualTo("redirect:/vacancies");
    }

    @Test
    public void whenDeleteVacancyThenGetError() {
        when(vacancyService.deleteById(1)).thenReturn(false);
        var model = new ConcurrentModel();
        var view = vacancyController.delete(model, 1);
        var actualMessage = model.getAttribute("message");
        assertThat(view).isEqualTo("errors/404");
        assertThat(actualMessage).isEqualTo("Вакансия с указанным идентификатором не найдена");
    }
}
