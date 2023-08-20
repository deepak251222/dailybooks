package com.api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="expenses_table")
public class Expenses {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
         private long expensesId;
         @DateTimeFormat(pattern = "yyyy-MM-dd")
         private Date date;
         private long expenseAmount;
         private String expenseCategory;
         @Lob
         private String files;
         private String notes;
         private long tax;
         private String vendors;

}
