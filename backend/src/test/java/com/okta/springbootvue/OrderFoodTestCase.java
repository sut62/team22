package com.okta.springbootvue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.okta.springbootvue.repository.*;
import com.okta.springbootvue.entity.*;

@DataJpaTest
public class OrderFoodTestCase {

    private Validator validator;

    @Autowired
    private OrderFoodRepository orderfoodRepository;
    @Autowired
    private TablesRepository tablesRepository;
    @Autowired
    private OrderTypeRepository ordertypeRepository;
    @Autowired
    private ManageMenuRepository managemenuRepository;
    @Autowired
    private OrderStatusRepository orderstatusRepository;

    tables table = new tables();
    OrderType types = new OrderType();
    ManageMenu managemenu = new ManageMenu();
    OrderStatus status = new OrderStatus();

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        
        table.setSeats(4);
        tablesRepository.saveAndFlush(table);

        types.setType("ห่อกลับบ้าน");
        ordertypeRepository.saveAndFlush(types);
        
        managemenu.setM_name("ข้าวมันไก่");
        managemenu.setM_price(45);
        managemenuRepository.saveAndFlush(managemenu);

        status.setStatus("เสิร์ฟอาหารเรียบร้อย");
        orderstatusRepository.saveAndFlush(status);

        
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
    @Test
    void B6014292_testOrderFoodSaveSuccess() {
        OrderFood  orderfood = new OrderFood();
        orderfood.setDishquantity(1);
        orderfood.setTables(table);
        orderfood.setManagemenu(managemenu);
        orderfood.setOrdertype(types);
        orderfood.setOrderstatus(status);

        orderfood = orderfoodRepository.saveAndFlush(orderfood);

        Optional<OrderFood>  found = orderfoodRepository.findById(orderfood.getId());
        assertEquals(1, found.get().getDishquantity());
        assertEquals(table, found.get().getTables());
        assertEquals(managemenu, found.get().getManagemenu());
        assertEquals(types, found.get().getOrdertype());
        assertEquals(status, found.get().getOrderstatus());
    }

    @Test
    void B6014292_testOrderFoodNegativeNumberCase(){
        OrderFood  orderfood = new OrderFood();
        orderfood.setDishquantity(-1);
        orderfood.setTables(table);
        orderfood.setManagemenu(managemenu);
        orderfood.setOrdertype(types);
        orderfood.setOrderstatus(status);

        Set<ConstraintViolation<OrderFood>> result = validator.validate(orderfood);

        assertEquals(1, result.size());

        ConstraintViolation<OrderFood> v = result.iterator().next();
        assertEquals("must be positive number", v.getMessage());
        assertEquals("dishquantity", v.getPropertyPath().toString());

    }

    
    @Test
    void B6014292_testOrderFoodDataNullCase(){
        OrderFood  orderfood = new OrderFood();
        orderfood.setDishquantity(null);
        orderfood.setTables(table);
        orderfood.setManagemenu(managemenu);
        orderfood.setOrdertype(types);
        orderfood.setOrderstatus(status);

        Set<ConstraintViolation<OrderFood>> result = validator.validate(orderfood);

        assertEquals(1, result.size());

        ConstraintViolation<OrderFood> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("dishquantity", v.getPropertyPath().toString());

    }
    
    @Test
    void B6014292_testOrderFoodMaxNumberCase(){
        OrderFood  orderfood = new OrderFood();
        orderfood.setDishquantity(1000);
        orderfood.setTables(table);
        orderfood.setManagemenu(managemenu);
        orderfood.setOrdertype(types);
        orderfood.setOrderstatus(status);


        Set<ConstraintViolation<OrderFood>> result = validator.validate(orderfood);

        assertEquals(1, result.size());

        ConstraintViolation<OrderFood> v = result.iterator().next();
        assertEquals("must not be more 100", v.getMessage());
        assertEquals("dishquantity", v.getPropertyPath().toString());

    }

    @Test
    void B6014292_testOrderFoodtablenullCase(){
        OrderFood  orderfood = new OrderFood();
        orderfood.setDishquantity(1);
        orderfood.setTables(null);
        orderfood.setManagemenu(managemenu);
        orderfood.setOrdertype(types);
        orderfood.setOrderstatus(status);


        Set<ConstraintViolation<OrderFood>> result = validator.validate(orderfood);

        assertEquals(1, result.size());

        ConstraintViolation<OrderFood> v = result.iterator().next();
        assertEquals("must not be null tables", v.getMessage());
        assertEquals("tables", v.getPropertyPath().toString());

    }

    @Test
    void B6014292_testOrderFoodManagemenunullCase(){
        OrderFood  orderfood = new OrderFood();
        orderfood.setDishquantity(1);
        orderfood.setTables(table);
        orderfood.setManagemenu(null);
        orderfood.setOrdertype(types);
        orderfood.setOrderstatus(status);


        Set<ConstraintViolation<OrderFood>> result = validator.validate(orderfood);

        assertEquals(1, result.size());

        ConstraintViolation<OrderFood> v = result.iterator().next();
        assertEquals("must not be null managemenu", v.getMessage());
        assertEquals("managemenu", v.getPropertyPath().toString());

    }

    @Test
    void B6014292_testOrderFoodOrderTypenullCase(){
        OrderFood  orderfood = new OrderFood();
        orderfood.setDishquantity(1);
        orderfood.setTables(table);
        orderfood.setManagemenu(managemenu);
        orderfood.setOrdertype(null);
        orderfood.setOrderstatus(status);


        Set<ConstraintViolation<OrderFood>> result = validator.validate(orderfood);

        assertEquals(1, result.size());

        ConstraintViolation<OrderFood> v = result.iterator().next();
        assertEquals("must not be null ordertype", v.getMessage());
        assertEquals("ordertype", v.getPropertyPath().toString());

    }

    @Test
    void B6014292_testOrderFoodOrderstatusnullCase(){
        OrderFood  orderfood = new OrderFood();
        orderfood.setDishquantity(1);
        orderfood.setTables(table);
        orderfood.setManagemenu(managemenu);
        orderfood.setOrdertype(types);
        orderfood.setOrderstatus(null);


        Set<ConstraintViolation<OrderFood>> result = validator.validate(orderfood);

        assertEquals(1, result.size());

        ConstraintViolation<OrderFood> v = result.iterator().next();
        assertEquals("must not be null orderstatus", v.getMessage());
        assertEquals("orderstatus", v.getPropertyPath().toString());

    }

    @Test
    void B6014292_testOrderStatusdataCase(){
        OrderStatus orderstatus = new OrderStatus();
        orderstatus.setStatus("เสิร์ฟอาหารเรียบร้อย");

        orderstatusRepository.saveAndFlush(orderstatus);

        Optional<OrderStatus>  found = orderstatusRepository.findById(orderstatus.getId());
        assertEquals("เสิร์ฟอาหารเรียบร้อย", found.get().getStatus());
       
    }

    @Test
    void B6014292_testOrderStatusNullCase(){
        OrderStatus orderstatus = new OrderStatus();
        orderstatus.setStatus(null);

        Set<ConstraintViolation<OrderStatus>> result = validator.validate(orderstatus);

        assertEquals(1, result.size());

        ConstraintViolation<OrderStatus> v = result.iterator().next();
        assertEquals("must not be status null", v.getMessage());
        assertEquals("status", v.getPropertyPath().toString());

    }

    @Test
    void B6014292_testOrderOrderTypedataCase(){
        OrderType ordertype = new OrderType();
        ordertype.setType("ห่อกลับบ้าน");

        ordertypeRepository.saveAndFlush(ordertype);

        Optional<OrderType>  found = ordertypeRepository.findById(ordertype.getId());
        assertEquals("ห่อกลับบ้าน", found.get().getType());
       
    }

    
    @Test
    void B6014292_testOrderTypeNullCase(){
        OrderType ordertype = new OrderType();
        ordertype.setType(null);

        Set<ConstraintViolation<OrderType>> result = validator.validate(ordertype);

        assertEquals(1, result.size());

        ConstraintViolation<OrderType> v = result.iterator().next();
        assertEquals("must not be type null", v.getMessage());
        assertEquals("type", v.getPropertyPath().toString());

    }
}
