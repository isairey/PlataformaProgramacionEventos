package org.ess.module.event.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
@NoArgsConstructor
public class EventModel {
    private long id;
    private String name;
    private String location;
    private String status;
    private String startDate;
    private String startTime;
    private String endDate;
    private String endTime;
    private String createdBy;
    private String updatedAt;
    private String createdAt;
}
