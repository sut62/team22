<template>
  <div class="home">
    <v-row justify="center">
      <div class="brown--text text--darken-1 headline mt-8">กรอกข้อมูลพนักงาน</div>
    </v-row>

    <v-row justify="center" class="mt-8">
      <v-col cols="6" sm="6" md="3" class="mt-0">
        <v-text-field
          label="ชื่อ-นามสกุล"
          v-model="Employee.name"
          :rules="[(v) => !!v || 'Item is required']"
          outlined
        ></v-text-field>
      </v-col>

    

      <v-col cols="6" sm="6" md="3" class="mt-0">
        <v-autocomplete
          label="อายุ"
          outlined
          v-model="Employee.ageId"
          :items="ages"
          item-text="age"
          item-value="id"
          :rules="[(v) => !!v || 'Item is required']"
          required
        ></v-autocomplete>
      </v-col>
    </v-row>

    <v-row justify="center">
      <v-col class="d-flex mt-0" cols="6" sm="3">
        <v-text-field
          label="เบอร์โทรศัพท์"
          v-model="Employee.tel"
          :rules="[(v) => !!v || 'Item is required']"
          outlined
        ></v-text-field>
      </v-col>

      <v-col cols="6" sm="6" md="3" class="mt-0">
        <v-autocomplete
          label="เพศ"
          outlined
          v-model="Employee.genderId"
          :items="genders"
          item-text="name"
          item-value="id"
          :rules="[(v) => !!v || 'Item is required']"
        ></v-autocomplete>
      </v-col>
    </v-row>

    <v-row justify="center">
      <v-col cols="6" sm="6" md="6" class="mt-0">
        <v-text-field
          label="ที่อยู่"
          v-model="Employee.address"
          :rules="[(v) => !!v || 'Item is required']"
          outlined
        ></v-text-field>
      </v-col>
    </v-row>

    <v-row justify="center">
      <v-col class="d-flex mt-0" cols="6" sm="3">
        <v-text-field
          label="เลขบัตรประชาชน"
          v-model="Employee.enum"
          :rules="[(v) => !!v || 'Item is required']"
          outlined
        ></v-text-field>
      </v-col>

      <v-col class="d-flex mt-0" cols="6" sm="3">
        <v-autocomplete
          label="สถานภาพ"
          outlined
          v-model="Employee.marital_statusId"
          :items="marital_Statuss"
          item-text="name"
          item-value="id"
          :rules="[(v) => !!v || 'Item is required']"
          required
        ></v-autocomplete>
        
      </v-col>
    </v-row>

    <v-row justify="center">
      <v-col cols="6" sm="6" md="3" class="mt-0">
        <v-menu
                  ref="menu"
                  v-model="menu"
                  :close-on-content-click="false"
                  :return-value.sync="date"
                  transition="scale-transition"
                  offset-y
                  full-width
                  min-width="290px"
                >
                  <template v-slot:activator="{ on }">
                    <v-text-field
                      v-model="date"
                      label="วัน/เดือน/ปีเกิด"
                      prepend-icon="event"
                      readonly
                      v-on="on"
                    ></v-text-field>
                  </template>
                  <v-date-picker v-model="date" locale="th" no-title scrollable>
                    <v-spacer></v-spacer>
                    <v-btn text color="primary" @click="menu = false">Cancel</v-btn>
                    <v-btn text color="primary" @click="$refs.menu.save(date)">OK</v-btn>
                  </v-date-picker>
                </v-menu>
      </v-col>

      <v-col cols="6" sm="6" md="3" class="mt-0">
        <v-autocomplete
          label="ตำแหน่ง"
          outlined
          v-model="Employee.positionId"
          :items="positions"
          item-text="name"
          item-value="id"
          :rules="[(v) => !!v || 'Item is required']"
          required
        ></v-autocomplete>
      </v-col>
    </v-row>

    

    <v-row justify="center">
      <v-btn @click="saveEmployee" :class="{ red: !valid, green: valid }">save</v-btn>
    </v-row>
  </div>
</template>



<script>

import https from '../plugins/https';



export default {
  name: "Employee",
  data() {
    return {
      Employee: {
        name: "",
        tel: "",
        address: "",
        birth: "",
        enum: "",
        eid: "",
        ageId: "",
        genderId: "",
        positionId: "",
        marital_statusId: ""
      },
      date: new Date().toISOString().substr(0, 10),
      headers: [{ text: "Employee", value: "employee.name" }],
      items: [],
      Ages: [],
      Marital_Statuss: [],
      Genders: [],
      Positions: [],
      valid: false
    };
  },
  methods: {
    /* eslint-disable no-console */
    getAges() {
      http
        .get("/Age")
        .then(response => {
          this.ages = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // ดึงข้อมูล Employee ใส่ combobox
    getPositions() {
      http
        .get("/Position")
        .then(response => {
          this.positions = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getGenders() {
      http
        .get("/Gender")
        .then(response => {
          this.genders = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // ดึงข้อมูล Delivery_Type ใส่ combobox
    getMarital_Statuss() {
      http
        .get("/Marital_Status")
        .then(response => {
          this.marital_Statuss = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getEmployees() {
      http
        .get("/Employee")
        .then(response => {
          this.items = response.data;
          console.log(this.items);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // function เมื่อกดปุ่ม submit
    saveEmployee() {

      var x = Math.floor((Math.random() * 10000000) + 1);

      http
        .post(
          "/Employee/" +
            this.Employee.ageId +
            "/" +
            this.Employee.genderId +
            "/" +
            this.Employee.positionId +
            "/" +
            this.Employee.marital_statusId +
            "/" +
            this.Employee.name +
            "/" +
            this.Employee.tel +
            "/" +
            this.Employee.address +
            "/" +
            this.date +
            "/" +
            this.Employee.enum +
            "/" + x
        )
        .then(response => {
          console.log(response);
        })
        .catch(e => {
          console.log(e);
        });
      this.submitted = true;
    },
    clear() {
      this.$refs.form.reset();
      this.customerCheck = false;
    },
    refreshList() {
      this.getAges();
      this.getGenders();
      this.getPositions();
      this.getMarital_Statuss();
      this.getEmployees();
    }
    /* eslint-enable no-console */
  },
  mounted() {
    this.getAges();
    this.getGenders();
    this.getPositions();
    this.getMarital_Statuss();
    this.getEmployees();
  }
};
</script>
