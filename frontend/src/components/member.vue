<template>
  <v-container>
    <br><br>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <h1 class="display-2 font-weight-light mb-3">สมัครสมาชิก</h1>
      </v-flex>
    </v-layout>
    <br><br>
    <v-row justify="center">
      <v-col cols="2">
      <v-select 
      label="คำนำหน้าชื่อ"
      v-model="Member.prefixID"
      :items="prefix"
      item-text="name"
      item-value="id"
      prepend-icon="mdi-human-male-female"
      >
      </v-select>
      </v-col>
      <v-col cols="6">
    <v-text-field
      v-model="Member.name"
      hint="ตัวอักษรความยาวไม่ต่ำกว่า10แต่ไม่เกิน30"
      label="ชื่อ-สกุล"  
    ></v-text-field>
      </v-col>
      <v-col cols="2">
      <v-select 
      label="เพศ"
      v-model="Member.genderID"
      :items="gender"
      item-text="name"
      item-value="id"
      >
      </v-select>
      </v-col>
    </v-row>

    <v-row justify="center">
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
    <v-text-field
      v-model="Member.tel"
      hint="เป็นตัวเลขเท่านั้น(ไม่มี -)"
      label="เบอร์โทร"  
       prepend-icon="mdi-dialpad"
    ></v-text-field>
    </v-row>

    <v-row justify="center">
    <v-text-field
      v-model="Member.mail"
      hint="อีเมลเท่านั้น"      
      label="E-mail"  
       prepend-icon="mdi-message-text"
    ></v-text-field>
    <v-select
      label="ประเภทสมาชิก"
      v-model="Member.memtypeID"
      :items="memtype"
      item-text="name"
      item-value="id"
      prepend-icon="mdi-table-chair"
    >
    </v-select>
    </v-row>
    
    <v-row justify="center">
    <v-select
      label="พนักงานที่ลงทะเบียน"
      v-model="Member.employeeID"
      :items="employee"
      item-text="e_name"
      item-value="id"
      prepend-icon="info"
    >
    </v-select>
    </v-row>
    <br><br>
    <v-row justify="center">
    <v-btn style="left: -8px" color="success" 
      @click="save"
    >ลงทะเบียน 
    <v-icon>mdi-floppy</v-icon>
    </v-btn>

    <card/>
   
    <v-btn style="right: -8px" color="success"
      @click="clear"
    >clear 
    <v-icon>mdi-close-circle</v-icon>
    </v-btn>
    </v-row>
    <v-alert v-if ="alert == 'success'"
      dense
      text
      type="success"
    >
      <strong>บันทึกข้อมูลสำเร็จ</strong>
    </v-alert>
    <v-alert v-else-if="alert == 'fail'"
      dense
      text
      type="error"
    >
      <strong>บันทึกข้อมูลไม่สำเร็จ กรุณาตรวจสอบความถูกต้องอีกครั้ง</strong>
    </v-alert>
    <br><br><br>
    <span class="display font-weight-light white--text">*สมัครเลยวันนี้ "ลูกค้าบัตรสมาชิกVIPลดเลยทันที 10% บัตรสมาชิกนักศึกษาลดเลยทันที 5% บัตรทั่วไปลดทันที 7%"</span>
    <br>
    <span class="display font-weight-light white--text">*นักศึกษาเพียงแสดงบัตร สมัครฟรี!! บัตรVIPมีค่าสมัครเพียง 200 บาท บัตรทั่วไปมีค่าสมัคร 100 บาท"</span>
  </v-container>
</template>

<script>
import http from "../plugins/https";


export default {
 name: "Member",
  data() {
    return {
      Member: {
        name: "",
        tel: "",
        mail: "",
        employeeID: "",
        genderID: "",
        prefixID: "",
        memtypeID: ""
      },
      date: new Date().toISOString().substr(0, 10),
      menu: false,
      prefix: [],
      employee: [],
      gender: [],
      memtype: [],
      items: [],
      valid: false,
      dialog: false,
      Check: false,
      memtypes: null,
      alert:''
    };
  },
  
  methods:{
     /* eslint-disable no-console */
    save(){
      http
        .post(
          "/members/" +
            this.Member.prefixID +
            "/" +
            this.Member.name +
            "/" +
            this.Member.genderID +
            "/" +
            this.date +
            "/" +
            this.Member.tel +
            "/" +
            this.Member.mail +
            "/" +
            this.Member.memtypeID +
            "/" +
            this.Member.employeeID 
             
        )
        .then(response => {
          console.log(response);
          this.alert='success'
        })
        .catch(e => {
          console.log(e);
          this.alert='fail'
        });
    },
    clear() {
      window.location.reload();
    },
    search(){
        http
        .get("/members/" + this.Member.tel)
        .then(response => {
          console.log(response);
          if (response.data != null) {
            this.memberName = response.data.name;
            this.memberTel = response.data.tel;
            this.memberMail = response.data.mail;
            var birth = response.data.birth.split("T");
            this.memberBirth = birth;
            this.memtypes = response.data.select_memtype.name;
            this.Check = response.status;
          }        
        })
        .catch(e => {
          console.log(e);
        });
    },
    getPrefix(){
       http
        .get("/prefixs")
        .then(response => {
          this.prefix = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getGender(){
       http
        .get("/Gender")
        .then(response => {
          this.gender = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getMemType(){
      http
        .get("/memtypes")
        .then(response => {
          this.memtype = response.data;
          console.log(this.items);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getEmployee(){
      http
        .get("/Employee")
        .then(response => {
          this.employee = response.data;
          console.log(this.items);
        })
        .catch(e => {
          console.log(e);
        });
    },
    refreshList() {
      this.getPrefix();
      this.getGender();
      this.getMemType();
      this.getEmployee();
    }
    /* eslint-enable no-console */
  },
  mounted() {
    this.getPrefix();
    this.getGender();
    this.getMemType();
    this.getEmployee();
  }
};
</script>
