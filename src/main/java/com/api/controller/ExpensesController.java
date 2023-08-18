package com.api.controller;
import com.api.entity.Expenses;
import com.api.service.Impl.ExpensesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpensesController {

      @Autowired
        private  ExpensesServiceImpl expensesService;

        @GetMapping("/{expensesId}")
        public ResponseEntity<?> getExpenses(@PathVariable long expensesId) throws Exception{
                Expenses expenses = expensesService.getExpenses(expensesId);
            return ResponseEntity.ok(expenses);
        }
        @PostMapping
        public ResponseEntity<Expenses> createExpenses(
                @ModelAttribute Expenses expenses,
                @RequestParam(value = "file", required = false) MultipartFile file
        ) throws IOException {
            Expenses createdExpenses = expensesService.createExpenses(expenses, file);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdExpenses);
        }

        @DeleteMapping("/{expensesId}")
        public ResponseEntity<Void> deleteExpenses(@PathVariable long expensesId) {
            boolean deleted = expensesService.deleteExpenses(expensesId);
            if (deleted) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        }
    @PutMapping("/{expensesId}")
        public ResponseEntity<Expenses> updateExpenses(
                @ModelAttribute Expenses expensesUpdate,
                @PathVariable long expensesId,
                @RequestParam(value = "file", required = false) MultipartFile file
        ) throws IOException {
            Expenses updatedExpenses = expensesService.updateExpenses(expensesUpdate, expensesId, file);
            return ResponseEntity.ok(updatedExpenses);
        }
    @GetMapping
    public ResponseEntity<List<Expenses>> getExpenses() throws Exception{
        List<Expenses> expenses = expensesService.getAllExpenses();
        return ResponseEntity.ok(expenses);
    }


}
