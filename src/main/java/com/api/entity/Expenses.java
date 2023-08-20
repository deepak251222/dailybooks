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
         private double expenseAmount;
         private String expenseCategory;
         @Nullable
         @Lob
         private String files;
         private String notes;
         private double tax;
         private String vendors;

}
