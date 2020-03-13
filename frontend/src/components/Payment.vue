<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-2 font-weight-bold mb-3">Welcome to Payment</h1>
      </v-flex>
    </v-layout>

<v-card
        class="mx-auto"
        max-width="1000"
        outlined>
  <v-col cols = 12>
     <v-simple-table
          fixed-header>
	<div class="text-uppercase text-bold"></div>
<table class="table table-striped table-hover">
    <thead>
        <tr>
            <th>
                <label class="form-checkbox">
                    <input :disabled="confirm" type="checkbox" v-model="selectAll" @click="select">
                    <i class="form-icon"></i>
                </label>
            </th>
            <th >โต๊ะ</th>
            <th>เมนู</th>
            <th>จำนวน</th>
            <th>สถานะ</th>
        </tr>
    </thead>
    <tbody>
        <tr v-for="i in orderfoods" v-bind:key= "i">
            <td>
                <label class="form-checkbox">
                    <input :disabled="confirm" type="checkbox" :value="i.id" v-model="selected" return-object>
                    <i class="form-icon"></i>
                </label>
            </td>
            <td>{{i.tables.id}}</td>
            <td>{{i.managemenu.m_name}}</td>
            <td>{{i.dishquantity}}</td>
            <td>{{i.orderstatus.status}}</td>
        </tr>
    </tbody>
</table>
     </v-simple-table>
  </v-col>
</v-card>

 
    
  <v-row justify="center">
    <v-btn @click="conFirm" color = "success">
       ยืนยัน
     </v-btn>
     <v-btn @click="confirm = false" color ="red">
       ยกเลิก
     </v-btn>
  </v-row>
    <v-row justify="center">
      <v-col cols="4">
        <v-form v-model="valid" ref="form">
            
            
            <v-row>
              <v-col cols="10">
                
                <v-select 
                  label="Membership"
                  outlined 
                  v-model="Payment.Selectmembership"
                  :items="memberships"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                  prepend-icon="mdi-human-male"
                  return-object
                ></v-select>
              </v-col>
            </v-row>



            <v-card-text>
              <v-container>
                <v-row>
                  <v-text-field
                    v-if="Payment.Selectmembership == 'เป็นสมาชิก' "
                    outlined
                    label="เบอร์โทรศัพท์"
                    v-model="tel"
                    :rules="[(v) => !!v || 'tel is required']"
                    required
                  ></v-text-field>
                </v-row>  
                  <p v-if="Check != ''">ชื่อลูกค้า : {{Payment.Selectmember.name}}</p>
                  <p v-if="Check != ''">ประเภทสมาชิก : {{Payment.Selectmember.select_memtype.name}}</p>
              </v-container>
            </v-card-text>

          <v-row >
            <v-spacer></v-spacer>
              <div class="my-2">
                <v-btn  v-if="Payment.Selectmembership == 'เป็นสมาชิก'" @click="findMember" depressed large color="primary">ค้นหา</v-btn>
              </div>
              <v-alert v-model="check" :type="type">
                <v-row>
                    <v-col class="grow">
                      {{text}}
                    </v-col>
                    <v-col class="shrink">
                      <v-btn
                      @click="check = false"

                      color="blue"
                    >
                    CLOSE
                    </v-btn>
                </v-col>
                </v-row>
              </v-alert>
          </v-row>


            <v-row>
              <v-col cols="10">
                <v-select
                  label="Employee"
                  outlined
                  v-model="Payment.Selectemployee"
                  :items="employees"
                  :rules="[(v) => !!v || 'Item is required']"
                  item-text='e_name'
                  item-value='id'
                  required
                  return-object
                  prepend-icon="mdi-human-male"
                ></v-select>
              </v-col>
            </v-row>
            <v-row >
              <div>
                <v-text-field
              label="ต้องจ่าย"
              prepend-icon="mdi-currency-usd"
              :value="price"
              dense
              readonly
            
              >


              </v-text-field>
              <v-text-field
              v-model.number="Payment.Money"      
              label="เงิน"  
              
              prepend-icon="mdi-dialpad"
              ></v-text-field>
              </div>
              
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
    <div>
      <v-alert  prominent v-model="snackbar" :type="type">
        <v-row align="center"> 
          <v-col class="grow">
            {{text}}
          </v-col>
          <v-col class="shrink">
            <v-btn
              @click="showReceipt"
              color="blue"
              :disabled="type == 'error'"
            >
            พิมพ์ใบเสร็จ
            </v-btn>
          </v-col>
        </v-row>
        
        
      </v-alert>
    </div>
    


  </v-container>


</template>

<script>

import http from "../plugins/https"
export default {
  name: "payment",
  data() {
    return {
      Payment: {
        Selecttable: "",
        Selectmember : {
        name:null,
        select_memtype:{
          name:null
        },
        

      },
        Total:0,
        Change:0,
        Money:0,
        Selectemployee: "",
        createDate:"",
      },
      tel: "",
      name:"",
      
      show:``,
      memberCheck: false,
      check:false,
		selected:[],
    selectAll: false,
    confirm:false,
    price:0,
    type:'',
      items: [],
      fooditems: [],
      valid: false,
      snackbar: false,
      text: '',
      nomem: '',
      

      orderfoods : []

      , employees : [], memberships : ['เป็นสมาชิก','ไม่เป็นสมาชิก'],
      totaldish:0

    };
      
  },
  methods: {
    /* eslint-disable no-console */

    // ดึงข้อมูล Employee ใส่ combobox
    conFirm(){
          this.confirm = true
          let sum = 0
          console.log(this.selected.length)
          for(let i of this.selected){
            console.log(i)
            sum +=this.orderfoods[i-1].dishquantity*this.orderfoods[i-1].managemenu.m_price
            this.totaldish +=this.orderfoods[i-1].dishquantity
            this.Payment.Selecttable=this.orderfoods[i-1].tables.id
            
            
          }
           
          this.price = sum
    },
    
    getOrderFoods() {
      http
        .get("/Order")
        .then(response => {
          this.orderfoods = response.data;
          
          
        })
        
    },
    

    // ดึงข้อมูล RentalType ใส่ combobox
    getEmployees() {
      http
        .get("/Employee")
        .then( response => {
          this.employees = response.data;
          
        })
    },

    findMember() {
      http.get("/members/" + this.tel).then(response => {
          
          if (response.data != null) {
            this.Payment.Selectmember= response.data;
            
            this.check = true;
            this.text = 'พบสมาชิก'
            this.type = 'success'
          }
          else{
            this.check = true;
            this.text = 'ไม่พบสมาชิก'
            this.type = 'error'
          }         
        })
        .catch(() => {
          
        });
 
    },
    showReceipt(){
      this.$router.push({
              name:'receipt',params:{
                change:this.Payment.Change,
                money:this.Payment.Money,
                table:this.Payment.Selecttable,
                total:this.Payment.Total,
                dish:this.totaldish,
                oders:this.selected,
                memtype:this.Payment.Selectmember.select_memtype.name,
                createDate:this.Payment.createDate

              }
          })
    },
    // function เมื่อกดปุ่ม record
    savePayment() {
        this.Payment.createDate = new Date()
        if(this.Payment.Selectmember.select_memtype.name == "บัตรนักเรียน/นักศึกษา"){
          this.Payment.Total = this.price-this.price*0.05 //5%
          this.Payment.Change = this.Payment.Money - this.Payment.Total
          
            http
        .post(
          "/Savepayment",
            {"money":this.Payment.Money,"total":this.Payment.Total,"change":this.Payment.Change,"selectemployee":this.Payment.Selectemployee.id,
              "selectmember":this.Payment.Selectmember.id,
              "selecttable":this.Payment.Selecttable,"date":this.Payment.createDate
              }
          
        )
        .then(() => {
          this.snackbar= true;
          this.text='save success '
          this.type = 'success'
          
          
          
          
          
          
          //window.location.reload();
          
          
        })
        .catch(() => {
          this.snackbar= true;
          this.text='save fail '
          this.type = 'error'
          

        });
        }
        else if(this.Payment.Selectmember.select_memtype.name == "ทั่วไป"){
          this.Payment.Total = this.price-this.price*0.07 //7%
          this.Payment.Change = this.Payment.Money - this.Payment.Total
          
            http
        .post(
          "/Savepayment",
            {"money":this.Payment.Money,"total":this.Payment.Total,"change":this.Payment.Change,"selectemployee":this.Payment.Selectemployee.id,
              
              "selectmember":this.Payment.Selectmember.id
              ,"selecttable":this.Payment.Selecttable,"date":this.Payment.createDate
              }
        )
        .then(() => {
          this.snackbar= true;
          this.text='save success '
          this.type = 'success'
          
          
          
          
          
          //window.location.reload();
          
          
        })
        .catch(() => {
          this.snackbar= true;
          this.text='save fail '
          this.type = 'error'
        });
        }
        else if(this.Payment.Selectmember.select_memtype.name == "VIP"){
          
          
          this.Payment.Total = this.price-this.price*0.10 //10%
          this.Payment.Change = this.Payment.Money - this.Payment.Total
          
         
            http
        .post(
          "/Savepayment",
            {"money":this.Payment.Money,"total":this.Payment.Total,"change":this.Payment.Change,"selectemployee":this.Payment.Selectemployee.id,
              "selectmember":this.Payment.Selectmember.id
              ,"selecttable":this.Payment.Selecttable,"date":this.Payment.createDate
              }
          
        )
        .then(() => {
          this.snackbar= true;
          this.text='save success '
          this.type = 'success'
          
         
          
          
          //window.location.reload();
          
          
        })
        .catch(() => {
         this.snackbar= true;
          this.text='save fail '
          this.type = 'error'

        });
        }

        else{
          this.Payment.Total = this.price
          this.Payment.Change = this.Payment.Money - this.Payment.Total
         
          

          http
          .post(
          "/Savepayment",
            {"money":this.Payment.Money,"total":this.Payment.Total,"change":this.Payment.Change,"selectemployee":this.Payment.Selectemployee.id,
              "selectmember": 0
              ,"selecttable":this.Payment.Selecttable,"date":this.Payment.createDate
              }
          
        )
        .then(()=> {
          this.snackbar= true;
          this.text='save success '
          this.type = 'success'
          
          
         
          
          
          //window.location.reload();
          
          
        })
        .catch(() => {
          this.snackbar= true;
          this.text='save fail '
          this.type = 'error'

        });
        }
      
      //this.submitted = true;
    },
    getOrderFood() {
      http
        .get("/Order")
        .then(response => {
          this.fooditems = response.data;
          
          
        })
        
    },
  select() {
      this.selected = {};
      
			if (!this.selectAll) {
				for (let i in this.orderfoods) {
          //this.select = this.orderfoods[i].dishquantity
          
            console.log(this.orderfoods[i])
          
          
				}
			}
		},
    refreshList() {
      this.getOrderFoods();
      this.getEmployees();
      
     
     
    }
    /* eslint-enable no-console */
  },
  mounted() {
    this.getOrderFoods();
    
    this.getEmployees();
    
    
  }
};
</script>
<style scoped>
 
</style>
