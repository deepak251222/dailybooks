package com.api.service.Impl;
import com.api.entity.Expenses;
import com.api.exception.ResourceNotFoundException;
import com.api.dao.ExpensesRepo;
import com.api.service.ExpensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class ExpensesServiceImpl implements ExpensesService {

    @Autowired
    private ExpensesRepo expensesRepo;

    String  IMAGE_DIR="C:\\Users\\Thinroot\\Desktop\\Projects\\Categories\\src\\main\\resources\\imgs\\";

    // Getting get expense by id
    @Override
    public Expenses getExpenses(long expensesId)throws Exception {
        Expenses expense = expensesRepo.findById(expensesId)
                .orElseThrow(() -> new ResourceNotFoundException("Expense with ID " + expensesId + " not found"));
        return expense;
    }
    // new create expenses
//    @Override
//    public Expenses createExpenses(Expenses expenses, MultipartFile file) throws IOException {
//        byte[] bytes = file.getBytes();
//        String originalFilename = file.getOriginalFilename();
//        String filePath =IMAGE_DIR + originalFilename;
//        try (FileOutputStream fos = new FileOutputStream(filePath)) {
//            fos.write(bytes);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//           throw  new ResourceNotFoundException("Error into file uploading @!");
//        }
//        expenses.setFiles(filePath);
//        return expensesRepo.save(expenses);
//    }
    @Override
    public Expenses createExpenses(Expenses expenses, MultipartFile file) throws IOException {
        try {
        if (file != null && !file.isEmpty()) {
            String originalFilename = file.getOriginalFilename();
            expenses.setFiles(originalFilename);
        }else{
            expenses.setFiles("empty");
        }
            return expensesRepo.save(expenses);
        } catch (DataAccessException e) {
            throw new ResourceNotFoundException("Error while saving expenses data.");
        }
    }

    // delete expenses
    @Override
    public void deleteExpenses(long expensesId) {
        Expenses expense = expensesRepo.findById(expensesId).orElseThrow(()->new ResourceNotFoundException("Expense with ID  " + expensesId + " not found"));
        expensesRepo.delete(expense);
    }
// update expenses
    @Override
    public Expenses updateExpenses(Expenses expensesUpdate, long expensesId, MultipartFile file)throws IOException {
            Expenses expenses = expensesRepo.findById(expensesId)
                    .orElseThrow(() -> new ResourceNotFoundException("Expenses with ID " + expensesId + " not found"));
            // Update the expenses details
            expenses.setDate(expensesUpdate.getDate());
            expenses.setExpenseAmount(expensesUpdate.getExpenseAmount());
            expenses.setExpenseCategory(expensesUpdate.getExpenseCategory());
            expenses.setNotes(expensesUpdate.getNotes());
            expenses.setTax(expensesUpdate.getTax());
            expenses.setVendors(expensesUpdate.getVendors());
            if (file != null && !file.isEmpty()) {
                String originalFilename = file.getOriginalFilename();
               // String filePath =IMAGE_DIR + originalFilename;
                expenses.setFiles(originalFilename);
            }
            return expensesRepo.save(expenses);
        }
        // get all expenses
    @Override
    public List<Expenses> getAllExpenses() {
        List<Expenses> expenses = expensesRepo.findAll();
        if (expenses.isEmpty()) {
            throw new ResourceNotFoundException("No Expenses Found");
        }
        return expenses;
    }
}
