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
          item-text="number"
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
          item-text="name"
          item-value="id"
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
          label="ขนาด"
          v-model="orderfood.dishsizeId"
          :items="dishsizes"
          item-text="size"
          item-value="id"
          :rules="[(v) => !!v || 'ยังไม่ได้เลือกขนาดจาน']"
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
           dishsizeId: "",
           dishnumber: ""
         },
        loading: true,
        headers: [
          {
            text: 'TABLE',
            align: 'left',
            sortable: false,
            value: 'tablenumber.number',
          },
          { text: 'MENU', value: 'menu.name' },
          { text: 'DishSize', value: 'dishsize.size' },
          { text: 'DishNumber', value: 'dishnumber' }
        ],
        valid : false,
        tablenumber: [],
        menus: [],
        dishsizes: []
      };
    },
    methods: {
      /* eslint-disable no-console */
      getTableNunber() {
         http
        .get("/TableNumber")
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
        .get("/Menu")
        .then(response => {
          this.menus = response.data;
          console.log(response.data);          
        })
        .catch(e => {
          console.log(e);
        });
      },
       getDishSize() {
         http
        .get("/DishSize")
        .then(response => {
          this.dishsizes = response.data;
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
            this.orderfood.dishsizeId
        )
        
        .then(response => {
          this.getTableNunber();
          this.getMenu();
          this.getDishSize();
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
        this.getDishSize();
        this.getOrderFood();
      }
       /* eslint-enable no-console */
    },
      mounted() {
        this.getTableNunber();
        this.getMenu();
        this.getDishSize();
        this.getOrderFood();
    }
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
