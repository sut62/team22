<template>
  <v-container>
    <v-card color="food">
    <v-card-title class="text-center justify-center py-6">
      <h1 class="font-weight-bold display-3 food--text">Food Order</h1>
    </v-card-title>

    <v-tabs
      background-color="transparent"
      color="food"
      grow
    >
      <v-tab>
        <div> MENU </div>
     </v-tab>
    </v-tabs>
  </v-card>

   <v-form v-model="valid" ref="form">
   <v-row align="center">
      <v-col class="d-flex" cols="12" sm="6">
         <v-select
          label="เลือกโต๊ะ"
          v-model="orderfood.tablenumberId"
          :items="tablenumbers"
          item-text="id"
          item-value="id"
          :rules="[(v) => !!v || 'ยังไม่ได้เลือกโต๊ะ']"
           solo
        ></v-select>
      </v-col>

      <v-col class="d-flex" cols="12" sm="6">
         <v-select
          label="เลือกเมนูอาหาร"
          v-model="orderfood.menuId"
          :items="menus"
          item-text="m_name"
          item-value="mnid"
          :rules="[(v) => !!v || 'ยังไม่ได้เลือกอาหาร']"
          solo
        ></v-select>
      </v-col>

      <v-col class="d-flex" cols="12" sm="6">
        <v-text-field
            label="จำนวน"
            v-model="orderfood.dishnumber"
            :rules="[(v) => !!v || 'ยังไม่ได้เลือกจำนวน']"
            solo
          ></v-text-field>
      </v-col>

      <v-col class="d-flex" cols="12" sm="6">
        <v-select
          label="ประเภท"
          v-model="orderfood.ordertypesId"
          :items="ordertypes"
          item-text="type"
          item-value="id"
          :rules="[(v) => !!v || 'ยังไม่ได้เลือกประเภท']"
          solo
        ></v-select>
      </v-col>
    </v-row>
   
      <div class = "text-center" cols="12" sm="4">
        <v-btn @click="saveOrderFood" :class="{ red: !valid, green: valid }" color=" white--text">SAVE</v-btn>
      </div>
    </v-form>

    <v-snackbar
      v-model="snackbar"
      top
    >
      {{ text }}
      <v-btn
        color="pink"
        text
        @click="snackbar = false"
      >
        Close
      </v-btn>
    </v-snackbar>
    
    <div>
    <v-data-table
      :headers="headers"
      :items="items"
      :items-per-page="5"
      :options.sync="options"
      :loading="loading"
      class="elevation-1 mt-9 food"
    >
      
    <template v-slot:item.action>
      <v-icon
        small
        class="mr-2"
        @click="dialog = true"
      >
        edit
      </v-icon>

      <v-dialog
      v-model="dialog"
      max-width="290"
    >
      <v-card>
        <v-card-title class="headline">Edit</v-card-title>

        <v-card-text>
          <v-col cols="12" sm="12" md="12">
                  <v-text-field v-model="orderfood.id" label="ID"></v-text-field>

                   <v-select
                    
                    label="Status"
                    v-model="orderfood.status"
                    :items="orderstatus"
                    item-text="status"
                    item-value="id"
                    :rules="[(v) => !!v || 'ยังไม่ได้เลือกstatus']"
                    solo
        ></v-select>
            </v-col>
        </v-card-text>

        <v-card-actions>
          <v-spacer></v-spacer>

          <v-btn
            color="red"
            text
            @click="dialog = false"     
          >
            ยกเลิก
          </v-btn>

          <v-btn
            color="green darken-1"
            text
            @click="editstatus"
          >
            ยืนยัน
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

     <v-icon
        small
        class="mr-2"
        @click="dialogremove = true"
      >
        delete
      </v-icon>

    <v-dialog
      v-model="dialogremove"
      max-width="290"
    >
      <v-card>
        <v-card-title class="headline">Delete</v-card-title>

        <v-card-text>
          <v-col cols="12" sm="12" md="12">
                  <v-text-field v-model="orderfood.id" label="ID"></v-text-field>
            </v-col>
        </v-card-text>

        <v-card-actions>
          <v-spacer></v-spacer>

          <v-btn
            color="red"
            text
            @click="dialogremove = false"
          >
            ยกเลิก
          </v-btn>

          <v-btn
            color="green darken-1"
            text
            @click="removeorder"
          >
            ลบ
          </v-btn>
      </v-card-actions>
      </v-card>
    </v-dialog>
    </template>
    
    </v-data-table>
  </div>


  </v-container>
</template>

<script>

import http from "../plugins/https";

  export default {
    
    name: "orderfood",
    
    data () {
      return {
         orderfood: {
           id: "",
           menuId: "",
           tablenumberId: "",
           ordertypesId: "",
           dishnumber: "",
           status: ""
         },
        loading: true,
        headers: [
          {
            text: 'ID',
            align: 'left',
            sortable: false,
            value: 'id',
          },
          { text: 'TABLE', value: 'tables.id' },
          { text: 'MENU', value: 'managemenu.m_name' },
          { text: 'OrderType', value: 'ordertype.type' },
          { text: 'DishNumber', value: 'dishquantity'},
          { text: 'Status', value: 'orderstatus.status'},
          { text: 'Actions', value: 'action', sortable: false },
          
        ],
        valid : false,
        tablenumber: [],
        menus: [],
        ordertypes: [],
        items : [],
        dialog: false,
        dialogremove: false,
        status : [],
        snackbar: false,
        text: ''
      };
    },
    methods: {
      
      /* eslint-disable no-console */
      getTableNunber() {
         http
        .get("/tableses")
        .then(response => {
          this.tablenumbers = response.data;
          console.log(response.data);          
        })
        .catch(e => {
          console.log(e);
        });
      },
       getMenu() {
         http
        .get("/manageMenus")
        .then(response => {
          this.menus = response.data;
          console.log(response.data);          
        })
        .catch(e => {
          console.log(e);
        });
      },
       getOrderType() {
         http
        .get("/OrderType")
        .then(response => {
          this.ordertypes = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
      },
       getOrderStatus() {
         http
        .get("/OrderStatus")
        .then(response => {
          this.orderstatus = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
      },
      saveOrderFood() {
      http
        .post(
          "/Order/" +
            this.orderfood.tablenumberId +
            "/"+
            this.orderfood.menuId +
            "/" +
            this.orderfood.dishnumber +
            "/" + 
            this.orderfood.ordertypesId +
            "/" +
            1
        )
        
        .then(response => {
          this.getTableNunber();
          this.getMenu();
          this.getOrderType();
          this.getOrderFood();
          this.clear();
          this.snackbar = true
          this.text = "บักทึกสำเร็จ"
          console.log(response);  
        })
        .catch(e => {
          this.snackbar = true
          this.text = "บันทึกไม่สำเร็จ"
          console.log(e);
        });
      this.submitted = true;
    },
    clear() {
      this.$refs.form.reset();
    },
    editstatus(){
       http
         .put(
           "/Order/" +
            this.orderfood.id +
            "/" +
            this.orderfood.status
         )
        .then(response => {
          this.getTableNunber();
          this.getMenu();
          this.getOrderType();
          this.getOrderFood();
          this.clear();
          this.dialog=false
          console.log(response);      
        })
        .catch(e => {
          console.log(e);
        });
        this.submitted = true;
    },
    removeorder(){
      http
        .delete("/Order/" + this.orderfood.id)
        .then(response => {
          this.getOrderFood();
          this.clear();
          this.dialogremove = false
          console.log(response);      
        })
        .catch(e => {
          console.log(e);
        });
        this.submitted = true;
    },

    getOrderFood() {
      http
        .get("/Order")
        .then(response => {
          this.items = response.data;
          console.log(this.items);
        })
        .catch(e => {
          console.log(e);
        });
    },

      refreshList() {
        this.getTableNunber();
        this.getMenu();
        this.getOrderType();
        this.getOrderFood();
        this.getOrderStatus();
        this.editstatus();
        this.removeorder();
      }
       /* eslint-enable no-console */
    },
      mounted() {
        this.getTableNunber();
        this.getMenu();
        this.getOrderType();
        this.getOrderFood();
        this.getOrderStatus();
        this.editstatus()
        this.removeorder();
    },
  };
</script>
<style>
/* Helper classes */
.food {
  background-color: #FFFBE6 !important;
}
.food--text {
  color: #356859 !important;
}
</style>
