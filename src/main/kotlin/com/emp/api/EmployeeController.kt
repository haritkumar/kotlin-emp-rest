package com.emp.api

import com.emp.entity.Employee
import com.emp.service.EmployeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/emp")
class EmployeeController @Autowired constructor(
    private val employeeService: EmployeeService
) {

    /**
     * Creates a new Employee.
     *
     * @param employee Employee object that holds the employee data
     * @return Employee object representing the newly created employee
     * @throws ServiceException when an error occurs during employee creation
     */
    @PostMapping
    fun create(@RequestBody employee: Employee): Employee {
        return employeeService.create(employee)
    }

    /**
     * Updates an existing Employee.
     *
     * @param id identifier of the Employee to be updated
     * @param employee Employee object that holds the updated Employee data
     * @return EmployeeDTO object representing the updated Employee
     * @throws ServiceException when an error occurs during Employee update
     * @throws EntityNotFoundException when Employee with the specified id cannot be found
     */
    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody employee: Employee): Employee {
        return employeeService.update(id, employee)
    }

    /**
     * Retrieves a employee by its id.
     *
     * @param id identifier of the employee to retrieve
     * @return employee object representing the employee
     * @throws EntityNotFoundException when employee with the specified id cannot be found
     */
    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Employee {
        return employeeService.findById(id)
    }

    /**
     * Retrieves a list of all employees.
     *
     * @return list of EmployeeDTO objects representing all employees
     * @throws ServiceException when an error occurs during employee retrieval
     */
    @GetMapping
    fun findAll(): List<Employee> {
        return employeeService.findAll()
    }

    /**
     * Deletes an employee by its id.
     *
     * @param id identifier of the employee to delete
     * @throws EntityNotFoundException when employee with the specified id cannot be found
     * @throws DataIntegrityViolationException when an error occurs during message deletion
     */
    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long) {
        employeeService.deleteById(id)
    }
}