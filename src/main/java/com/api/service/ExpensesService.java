package com.api.service;

import com.api.entity.Expenses;
import com.api.exception.ResourceNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ExpensesService {


    public Expenses getExpenses(long expensesId)throws Exception;

    public Expenses createExpenses(Expenses expenses, MultipartFile file) throws IOException;

    public boolean deleteExpenses(long expensesId);

    public Expenses updateExpenses(Expenses expenses, long expensesId,MultipartFile file)throws IOException;

    public List<Expenses> getAllExpenses();


}
