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
                  item-text="managemenu.m_name"
                  item-value="id"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                  prepend-icon="mdi-cart"
                ></v-select>
              </v-col>
            </v-row>

            <v-row>
              <v-col cols="10">
                <v-select @change="savePayment($event)"
                  label="Membership"
                  outlined 
                  v-model="payment.membershipId"
                  :items="memberships"
                  item-text="namembs"
                  item-value="idmbs"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                  prepend-icon="mdi-human-male"
                ></v-select>
              </v-col>
            </v-row>



            <v-row >
              <v-text-field
                v-if="payment.membershipId == 1"              
                outlined
                label="ไอดีลูกค้า"
                v-model="payment.memberId"
                :rules="[(v) => !!v || 'กรุณากรอกข้อมูล']"
                required
              ></v-text-field>
              <p  v-for="mem in member" :key="mem">ชื่อสมาชิก : {{mem.name}} <br/> </p>
              
            </v-row>
            <v-row> <p  v-for="mem in member" :key="mem">ประเภทสมาชิก : {{mem.name}}</p></v-row>

          <v-row >
              <div class="my-2">
                <v-btn  v-if="payment.membershipId == 1" @click="findMember" depressed large color="primary">ค้นหา</v-btn>
              </div>
          </v-row>


            <v-row>
              <v-col cols="10">
                <v-select
                  label="Employee"
                  outlined
                  v-model="payment.employeeId"
                  :items="employees"
                  item-text="e_name"
                  item-value="id"
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

import http from "../plugins/https"
export default {
  name: "payment",
  data() {
    return {
      payment: {
        orderfoodId: "",
        membershipId: "",
        memberId: "",
        employeeId: ""
      },
      member: [],
      memberCheck: false,
      check:false,

      headers: [

        {
            text: 'OrderFood',
            align: 'left',
            sortable: false,
            value: 'selectorderfood.managemenu.m_name',
          },

        { text: "Price", value: "selectorderfood.managemenu.m_price" },
        { text: "Member", value: "selectmember.name" },
        { text: "Employee", value: "selectemployee.e_name" },
        
        
      ],

      items: [],

      valid: false,

      orderfoods : [] , members : [] , employees : [], memberships : [],

    };
      
  },

    
  methods: {
    /* eslint-disable no-console */

    // ดึงข้อมูล Employee ใส่ combobox
    getOrderFoods() {
      http
        .get("/Order")
        .then(response => {
          this.orderfoods = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // ดึงข้อมูล member ใส่ combobox
    getMemberships() {
      http
        .get("/memberships")
        .then(response => {
          this.memberships = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    getMembers() {
      http
        .get("/members")
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
        .get("/Employee")
        .then(response => {
          this.employees = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    findMember() {
      http
        .get("/members/" + this.payment.memberId)
        .then(response => {
          console.log(response);
          if (response.data != null) {
             this.member = response.data
          } else {
            this.clear()
          }          
        })
        .catch(e => {
          console.log(e);
        });
 
    },

    // function เมื่อกดปุ่ม record
    savePayment(event) {
      if(event == 2)
      this.payment.memberId = 0;
      http
        .post(
          "/payment/" +
            this.payment.orderfoodId +
            "/" +
            this.payment.memberId +
            "/" +
            this.payment.employeeId +
            "/" +
            this.payment.memberId,
        )
        .then(response => {
          this.getOrderFoods();
          this.getMemberships();
          this.getEmployees();
          this.getMembers();
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
      this.memberCheck = false;
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
      this.getMemberships();
      this.getEmployees();
      this.getMembers();
      this.getPayments();
    }
    /* eslint-enable no-console */
  },
  mounted() {
    this.getOrderFoods();
    this.getMemberships();
    this.getEmployees();
    this.getMembers();
    this.getPayments();
  }
};
</script>
