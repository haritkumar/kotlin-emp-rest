package com.emp.entity

import jakarta.persistence.*

@Entity
@Table(name = "EMPLOYEE")
class Employee : BaseEntity() {

    @Column(name = "empName")
    var empName: String? = "NA"

    @Column(name = "empDept")
    var empDept: String? = "NA"

    @Column(name = "empSalary")
    var empSalary: Double? = 0.0

    @Column(name = "empActive")
    var empActive: Boolean? = false

}