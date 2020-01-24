<template>
  <v-dialog v-model="dialog" persistent max-width="600px">
      <template v-slot:activator="{ on }">
        <v-btn color="success" dark v-on="on">ค้นหาลูกค้า<v-icon>mdi-magnify</v-icon></v-btn>
      </template>
      <v-card>
        <v-card-title>
          <span class="headline">Member Profile</span><v-icon>mdi-account-multiple</v-icon>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-text-field
                outlined
                label="เบอร์โทรศัพท์"
                v-model="Member.tel"
                :rules="[(v) => !!v || 'Item is required']"
                required
              ></v-text-field>
            </v-row>  
              <p v-if="Check != ''">ชื่อลูกค้า<v-icon>mdi-account</v-icon> : {{memberName}}</p>
              <p v-if="Check != ''">เบอร์โทรศัพท์<v-icon>mdi-cellphone</v-icon> : {{memberTel}}</p>
              <p v-if="Check != ''">อีเมล<v-icon>mdi-mail</v-icon> : {{memberMail}}</p>
              <p v-if="Check != ''">วันเกิด(ป-ด-ว)<v-icon>mdi-calendar-text</v-icon> : {{memberBirth[0]}}</p>
              <p v-if="Check != ''">ประเภทสมาชิก<v-icon>mdi-emoticon-cool</v-icon> : {{memtypes}}</p>
            
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="search">search</v-btn>
          <v-btn color="blue darken-1" text @click="Member.tel=null">clear</v-btn>
          <v-btn color="blue darken-1" text @click="dialog = false">close</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
</template>

<script>
import http from "../plugins/https";

export default {
    name: "memberfind", 
    data(){
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
        dialog: false,
        Check: false,
        }
    },
    
    methods:{
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
    }
    }
}
</script>