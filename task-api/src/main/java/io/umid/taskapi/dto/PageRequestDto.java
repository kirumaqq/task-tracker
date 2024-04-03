package io.umid.taskapi.dto;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Sort;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class PageRequestDto {

    int page = 1;
    int size = 10;
    String[] sortBy;
    Sort.Direction direction = Sort.Direction.ASC;


    public void setSortBy(String sortBy) {
        this.sortBy = sortBy.split(" ");
    }
}
