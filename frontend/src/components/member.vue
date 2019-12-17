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
      <v-combobox 
      label="คำนำหน้าชื่อ"
      v-modle="member.prefixs"
      :items="prefix"
      prepend-icon="mdi-human-male-female"
      >
      </v-combobox>
      </v-col>
      <v-col cols="6">
    <v-text-field
      v-model="member.name"      
      label="ชื่อ-สกุล"  
    ></v-text-field>
      </v-col>
      <v-col cols="2">
      <v-combobox 
      label="เพศ"
      v-modle="member.genders"
      :items="gender"
      >
      </v-combobox>
      </v-col>
    </v-row>

    <v-row justify="center">
		<v-menu max-width="290" >
      <template v-slot:activator="{ on }">
        <v-text-field
        readonly
        label="วันเดือนปีเกิด"
        prepend-icon="mdi-calendar-range"
        :value="formattedDate"
        v-on="on"
      ></v-text-field>
      </template>
      <v-date-picker v-model="date" ></v-date-picker>
    </v-menu>
    <v-text-field
      v-model="member.tel"      
      label="เบอร์โทร"  
       prepend-icon="mdi-dialpad"
    ></v-text-field>
    </v-row>

    <v-row justify="center">
    <v-text-field
      v-model="member.mail"      
      label="E-mail"  
       prepend-icon="mdi-message-text"
    ></v-text-field>
    <v-combobox
      label="ประเภทสมาชิก"
      v-model="member.memtype"
      :items="memtype"
      prepend-icon="mdi-table-chair"
    >
    </v-combobox>
    </v-row>
    
    <v-row justify="center">
    <v-combobox
      label="พนักงานที่ลงทะเบียน"
      v-modle="member.employees"
      :items="employee"
      prepend-icon="info"
    >
    </v-combobox>
    </v-row>
    <br><br>
    <v-row justify="center">
    <v-btn color="success"
      @click="save"
      
    >ลงทะเบียน 
    <v-icon>mdi-floppy</v-icon>
    </v-btn>
    </v-row>
    <br><br><br>
    <span class="display font-weight-light white--text">*สมัครเลยวันนี้ "ลูกค้าบัตรสมาชิกVIPลดเลยทันที 10% บัตรสมาชิกนักศึกษาลดเลยทันที 7%"</span>
    <br>
    <span class="display font-weight-light white--text">*นักศึกษาเพียงแสดงบัตร สมัครฟรี!! บัตรVIPมีค่าสมัครเพียง 200 บาท"</span>
  </v-container>
  
</template>

<script>
import format from 'date-fns/format'
import parselISO from 'date-fns/parseISO'
export default {
  name: 'HelloWorld',
  
  data: () => ({
        date:null,
        HH:['10','11','12','13','14','15','16','17','18','19','20','21','22'],
        MM:["00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59"],
        SS:["00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59"],
        member:{
          time:null,
          
        },
        
  }),
  
  computed:{
    formattedDate(){
      return this.date ? format(parselISO(this.date), 'do MMM YYY') : ''
    }
  },
  methods:{
    save(){

    },
    getPrefix(){

    },
    getMemType(){

    },
    getEmployee(){

    },

    
  }
   
}
</script>
