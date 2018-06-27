package control.self.igor.algorithms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import control.self.igor.algorithms.exception.BadRequestException;
import control.self.igor.algorithms.model.report.AlgorithmsTestsReport;
import control.self.igor.algorithms.service.test.sorting.SortingAlgorithmsTestService;

@RestController
@RequestMapping("/sorting")
public class SortingAlgoritmsController {

    private static final int DEFAULT_MAXIMAL_TO_SORT_LIST_SIZE = 1000;
    private SortingAlgorithmsTestService testService;

    @Autowired
    public SortingAlgoritmsController(SortingAlgorithmsTestService testService) {
	this.testService = testService;
    }

    @GetMapping("/bubble-sort")
    public AlgorithmsTestsReport bubbleSort(@RequestParam("testsNumber") int testsNumber,
	    @RequestParam(name = "maximalToSortListSize", required = false) Integer maximalToSortListSize) {
	if (testsNumber < 1) {
	    throw BadRequestException.createNotPositiveNumberException();
	}
	if (maximalToSortListSize == null || maximalToSortListSize < 1) {
	    maximalToSortListSize = DEFAULT_MAXIMAL_TO_SORT_LIST_SIZE;
	}
	return testService.testBubbleSort(testsNumber, maximalToSortListSize);
    }

}
