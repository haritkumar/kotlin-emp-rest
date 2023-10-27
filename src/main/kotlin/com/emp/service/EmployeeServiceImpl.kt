package com.emp.service

import com.emp.entity.Employee
import com.emp.repo.EmployeeRepository
import jakarta.persistence.EntityNotFoundException
import org.hibernate.service.spi.ServiceException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service

@Service
class EmployeeServiceImpl @Autowired constructor(
    private val repository: EmployeeRepository
) : EmployeeService {

    companion object {
        const val EMPLOYEE_NOT_FOUND = "There is no employee found with id: "
    }

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    /**
     * Creates a new Employee.
     *
     * @param entity Employee object that holds the Employee data
     * @return Employee object representing the newly created Employee
     * @throws ServiceException when an error occurs during Employee creation
     */
    override fun create(entity: Employee): Employee {
        try {
            return repository.save(entity)
        } catch (e: Exception) {
            log.error("Error persisting a new Employee: {}", e.message, e)
            throw ServiceException("Error persisting a new Employee", e)
        }
    }


    /**
     * Updates an existing Employee.
     *
     * @param id identifier of the Employee to be updated
     * @param entity Employee object that holds the updated Employee data
     * @return EmployeeDTO object representing the updated Employee
     * @throws ServiceException when an error occurs during Employee update
     * @throws EntityNotFoundException when Employee with the specified id cannot be found
     */
    override fun update(id: Long, entity: Employee): Employee {
        try {
            val persistedEntity = repository.findById(id).orElseThrow { EntityNotFoundException(EMPLOYEE_NOT_FOUND + id) }
            updateFields(persistedEntity, entity)
            persistedEntity.id = id
            return repository.save(persistedEntity)
        } catch (e: Exception) {
            log.error("Error updating a Employee: {}", e.message, e)
            throw ServiceException("Error updating a Employee", e)
        }
    }

    /**
     * Updates the fields of a persisted Employee entity with the values from a new Employee entity.
     *
     * @param persistedEntity The persisted Employee entity to be updated
     * @param newEntity The new Employee entity containing the updated values
     */
    private fun updateFields(persistedEntity: Employee, newEntity: Employee) {
        persistedEntity.empName = newEntity.empName
        persistedEntity.empDept = newEntity.empDept
        persistedEntity.empActive = newEntity.empActive
        persistedEntity.empSalary = newEntity.empSalary
    }

    /**
     * Retrieves an Employee by its id.
     *
     * @param id the id of the Employee to find
     * @return the Employee with the specified id
     * @throws EntityNotFoundException if the Employee with the given id is not found
     */
    override fun findById(id: Long): Employee {
        return repository.findById(id).orElseThrow { EntityNotFoundException(EMPLOYEE_NOT_FOUND + id) }
    }

    /**
     * Retrieves a list of all Employee.
     *
     * @return list of EmployeeDTO objects representing all Employee
     * @throws ServiceException when an error occurs during Employee retrieval
     */
    override fun findAll(): List<Employee> {
        try {
            val employees = repository.findAll()
            return employees.toList()
        } catch (e: Exception) {
            log.error("Error retrieving all existing Employees: {}", e.message, e)
            throw ServiceException("Error retrieving all existing Employees", e)
        }
    }

    /**
     * Deletes an Employee by its id.
     *
     * @param id identifier of the Employee to delete
     * @throws EntityNotFoundException when Employee with the specified id cannot be found
     * @throws DataIntegrityViolationException when an error occurs during Employee deletion
     */
    override fun deleteById(id: Long) {
        repository.findById(id).orElseThrow { EntityNotFoundException(EMPLOYEE_NOT_FOUND + id) }
        try {
            repository.deleteById(id)
        } catch (e: DataIntegrityViolationException) {
            log.error("Error deleting Employee with id: " + id + " - " + e.message, e)
        }
    }
}