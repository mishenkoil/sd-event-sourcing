package ru.mishenkoil.sd.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.mishenkoil.sd.model.domain.AttendanceStatReport;
import ru.mishenkoil.sd.model.domain.DailyAttendanceReport;
import ru.mishenkoil.sd.model.query.GetDailyAttendanceQuery;
import ru.mishenkoil.sd.model.query.GetAttendanceStatQuery;
import ru.mishenkoil.sd.service.query.ReportQueryHandler;

@RestController
@RequestMapping("/report")
public class ReportEndpoint {

    private final ReportQueryHandler reportQueryHandler;

    @Autowired
    public ReportEndpoint(ReportQueryHandler reportQueryHandler) {
        this.reportQueryHandler = reportQueryHandler;
    }

    @GetMapping("/daily")
    public DailyAttendanceReport getDailyAttendance(
            @RequestParam long id
    ) {
        return reportQueryHandler.getDailyAttendance(
                new GetDailyAttendanceQuery(id)
        );
    }

    @GetMapping("/mean")
    public AttendanceStatReport getAttendanceStat(
            @RequestParam long id
    ) {
        return reportQueryHandler.getAttendanceStat(
                new GetAttendanceStatQuery(id)
        );
    }
}
