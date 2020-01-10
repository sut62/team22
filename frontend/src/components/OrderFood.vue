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

    <div>
    <v-data-table
      :headers="headers"
      :items="items"
      :items-per-page="5"
      :options.sync="options"
      :loading="loading"
      class="elevation-1 mt-9 food"
    ></v-data-table>
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
           menuId: "",
           tablenumberId: "",
           ordertypesId: "",
           dishnumber: ""
         },
        loading: true,
        headers: [
          {
            text: 'TABLE',
            align: 'left',
            sortable: false,
            value: 'tables.id',
          },
          { text: 'MENU', value: 'managemenu.m_name' },
          { text: 'OrderType', value: 'ordertype.type' },
          { text: 'DishNumber', value: 'dishnumber' }
        ],
        valid : false,
        tablenumber: [],
        menus: [],
        ordertypes: []
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
            this.orderfood.ordertypesId
        )
        
        .then(response => {
          this.getTableNunber();
          this.getMenu();
          this.getOrderType();
          this.getOrderFood();
          this.clear();
          console.log(response);
        })
        .catch(e => {
          console.log(e);
        });
      //location.reload();  
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
      }
       /* eslint-enable no-console */
    },
      mounted() {
        this.getTableNunber();
        this.getMenu();
        this.getOrderType();
        this.getOrderFood();
    },
      created() {
        this.getOrderFood();
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
