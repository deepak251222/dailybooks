package com.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
         private Date date;
         private double expenseAmount;
         private String expenseCategory;
         @Lob
         private String files;
         private String notes;
         private double tax;
         private String vendors;

}
