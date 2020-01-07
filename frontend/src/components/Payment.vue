<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-2 font-weight-bold mb-3">Welcome to Payment</h1>
      </v-flex>
    </v-layout>

    <v-row justify="center">
      <v-col cols="4">
        <v-form v-model="valid" ref="form">

          
            <v-row>
              <v-col cols="10">
                <v-select
                  label="OrderFood"
                  outlined
                  v-model="payment.orderfoodId"
                  :items="orderfoods"
                  item-text="nameorderfood"
                  item-value="id_orderfood"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                  prepend-icon="mdi-cart"
                ></v-select>
              </v-col>
            </v-row>

            <v-container fluid>
              <v-radio-group v-model="row" row>
                <v-radio label="เป็นสมาชิก" color="success" value="radio-1"></v-radio>
                <v-radio label="ไม่เป็นสมาชิก" color="error" value="radio-2"></v-radio>
              </v-radio-group>
            </v-container>

          

        
            <v-row>
              <v-col cols="10">
                <v-select
                  label="Member"
                  outlined
                  v-model="payment.memberId"
                  :items="members"
                  item-text="namemember"
                  item-value="id_member"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                  prepend-icon="mdi-human-male"
                ></v-select>
              </v-col>
            </v-row>


            <v-row>
              <v-col cols="10">
                <v-select
                  label="Employee"
                  outlined
                  v-model="payment.employeeId"
                  :items="employees"
                  item-text="nameemployee"
                  item-value="id_employee"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                  prepend-icon="mdi-human-male"
                ></v-select>
              </v-col>
            </v-row>


            <v-row justify="center">
              <v-col cols="12">
                <v-btn @click="savePayment" :class="{ red: !valid, green: valid }">ตกลง</v-btn>
                <v-btn style="margin-left: 15px;" @click="clear">clear</v-btn>
              </v-col>
            </v-row>
            <br />
        
        </v-form>
      </v-col>
    </v-row>
  

    <v-row justify="center">
      <v-col cols="8">
        <v-data-table :headers="headers" :items="items" :items-per-page="5" class="elevation-1">
        </v-data-table>
      </v-col>
    </v-row>

  </v-container>

</template>

<script>
import http from "../plugins/https";

export default {
  name: "payment",
  data() {
    return {
      payment: {
        orderfoodId: "",
        memberId: "",
        employeeId: ""
      },
      memberCheck: false,

      headers: [

        {
            text: 'OrderFood',
            align: 'left',
            sortable: false,
            value: 'selectorderfood.nameorderfood',
          },


        { text: "Member", value: "selectmember.namemember" },
        { text: "Employee", value: "selectemployee.nameemployee" },
        
      ],

      items: [],

      valid: false,

      orderfoods : [] , members : [] , employees : [], memberName: ""

    };
      
  },

    
  methods: {
    /* eslint-disable no-console */

    // ดึงข้อมูล Employee ใส่ combobox
    getOrderFoods() {
      http
        .get("/orderfood")
        .then(response => {
          this.orderfoods = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // ดึงข้อมูล member ใส่ combobox
    getMembers() {
      http
        .get("/member")
        .then(response => {
          this.members = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // ดึงข้อมูล RentalType ใส่ combobox
    getEmployees() {
      http
        .get("/employee")
        .then(response => {
          this.employees = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // function เมื่อกดปุ่ม record
    savePayment() {
      http
        .post(
          "/payment/" +
            this.payment.orderfoodId +
            "/" +
            this.payment.memberId +
            "/" +
            this.payment.employeeId,
           
          
        )
        .then(response => {
          this.getOrderFoods();
          this.getMembers();
          this.getEmployees();
          this.getPayments();
          this.clear();
          console.log(response);
          //window.location.reload();
          
          
        })
        .catch(e => {
          console.log(e);
        });
      //this.submitted = true;
    },
    clear() {
      this.$refs.form.reset();
      this.customerCheck = false;
    },
    getPayments() {
      http
        .get("/payment")
        .then(response => {
          this.items = response.data;
          console.log(this.items);
        })
        .catch(e => {
          console.log(e);
        });
    },
    refreshList() {
      this.getOrderFoods();
      this.getMembers();
      this.getEmployees();
      this.getPayments();
    }
    /* eslint-enable no-console */
  },
  mounted() {
    this.getOrderFoods();
    this.getMembers();
    this.getEmployees();
    this.getPayments();
  }
};
</script>
