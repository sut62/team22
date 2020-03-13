<template >

  <v-container>
    
  <div class="home">
    
    <v-row justify="center">
      <v-col cols="6" sm="6" md="3" class="mt-0 ">
        </v-col>
    </v-row>

    <v-row justify="center">
      <div class="black--text text--white headline mt-8 ">Employee Register</div>
    </v-row>

    <v-row justify="center" class="mt-8">
     <v-col cols="6" sm="6" md="3" class="mt-0 white"></v-col>
     <v-col cols="6" sm="6" md="3" class="mt-0 white"></v-col>
    </v-row>

    <v-row justify="center" class="mt-0">
      
      <v-col cols="6" sm="6" md="3" class="mt-0 white">
        <v-text-field
          label="ชื่อ-นามสกุล"
          v-model="Employee.name"
          :rules="[(v) => !!v || 'Item is required']"
          outlined
        ></v-text-field>
      </v-col>

    

      <v-col cols="6" sm="6" md="3" class="mt-0 white">
        
       <v-chip
      class="ma-2"
      label
    >
    อายุ
    {{this.age}}
    ปี
    </v-chip>
      </v-col>
    </v-row>

    <v-row justify="center ">
      <v-col class="d-flex mt-0  white" cols="6" sm="3">
        <v-text-field
          label="เบอร์โทรศัพท์"
          v-model="Employee.tel"
          :rules="[(v) => !!v || 'Item is required']"
          outlined
        ></v-text-field>
      </v-col>

      <v-col cols="6" sm="6" md="3" class="mt-0 white">
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
      <v-col cols="6" sm="6" md="6" class="mt-0 white">
        <v-text-field
          label="ที่อยู่"
          v-model="Employee.address"
          :rules="[(v) => !!v || 'Item is required']"
          outlined
        ></v-text-field>
      </v-col>
    </v-row>

    <v-row justify="center">
      <v-col class="d-flex mt-0 white" cols="6" sm="3">
        <v-text-field
          label="เลขบัตรประชาชน"
          v-model="Employee.enum"
          :rules="[(v) => !!v || 'Item is required']"
          outlined
        ></v-text-field>
      </v-col>

      <v-col class="d-flex mt-0 white" cols="6" sm="3">
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
      <v-col cols="6" sm="6" md="3" class="mt-0 white">
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
                    <v-btn text color="primary" @click="$refs.menu.save(date)+MakeAge()">OK</v-btn>
                  </v-date-picker>
                </v-menu>
      </v-col>

      <v-col cols="6" sm="6" md="3" class="mt-0 white">
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
      
      
      <v-col cols="6" sm="6" md="6" class="mt-0 white">
          <v-row justify="center">

            <v-btn
      class=""
      :loading="loading"
      :disabled="loading"
      color="secondary"
      @click="check13digit"
    >
      Check Number
        </v-btn>
      <v-col cols="6" sm="6" md="3"  class="mt-0 white">
        <div v-html="check"></div>
      </v-col>
        <v-btn
          v-if="showsave"
          color="secondary"
          dark
          
          v-on="on"
          @click="saveEmployee"
        >
          บันทึก
        </v-btn>
        <v-col cols="6" sm="6" md="3"  class="mt-0 white">
        <div v-html="fail"></div>
      </v-col>
        
     </v-row>
    

      </v-col>
      
    </v-row>
    
    
    <div v-if="show">
      <div class="text-center">
    <v-dialog
      v-model="dialog"
      width="500"
    >
     

      <v-card>
        <v-card-title
          class="headline grey lighten-2"
          primary-title
        >
          YOUR QRcode
        </v-card-title>

        <v-card-text>
          <v-row justify="center">
          <img  ma-5 :src="imge">
          </v-row>
        </v-card-text>

        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="primary"
            text
            @click="clear"
          >
            OK
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>

    </div>
    
    
    <v-col cols="6" sm="6" md="3" class="mt-0"></v-col>
  </div>

  </v-container>
</template>



<script>

import http from "../plugins/https";



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
      showsave:false,
      dialog:false,
      imge:null,
      show:false,
      check:'',
      age:'',
      fail:'',
      date: new Date().toISOString().substr(0, 10),
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
    getImg(){
      var text = this.Employee.enum;
      http.get("/image/"+text).then( doc => {
        console.log(doc.data.content)
        this.imge = "http://localhost:9000/image/"+this.Employee.enum;
        
      }

      )
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
    MakeAge(){
      var today = new Date();
      console.log(today)
    var birthDate = new Date(this.date);
    console.log(birthDate)
    var age = today.getFullYear() - birthDate.getFullYear();
    var m = today.getMonth() - birthDate.getMonth();
    if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
        age--;
    }
    return this.age=age;
    },
    check13digit(){
        this.showsave = false;
        this.check = ''
        var text = this.Employee.enum;
        if(text.length==13){
          this.check = '<FONT color="#3EFF99" size="4"> <p>Correct</p></FONT>'
          this.showsave = true;
        }
        else if(text.length!=13){
          this.check = '<FONT color="#FF0000" size="4"> <p>Wrong</p></FONT>'
        }
        this.fail = ''
      
    },
    // function เมื่อกดปุ่ม submit
    saveEmployee() {
      http
        .post(
          "/Employee/" +
            this.age +
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
            this.Employee.enum
        )
        .then(response => {
          console.log(response);
          this.getImg()
          this.dialog=true
          this.show = true
          this.fail = ''
          
        })
        .catch(e => {
          console.log(e);
          this.fail = '<FONT color="#FF0000" size="4"> <p>Register Fail</p></FONT>'
        });
    },
    clear() {
      window.location.reload()
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

