<template>
  <div class="mainpage">
    <div class="outside">
      <br>
      <v-col cols = 12>
        <v-container>
          <v-layout row wrap>
            <v-col cols = 3>
              <v-text-field
              v-model="ManageMenu.mname"
              :counter="20"
              label="Menu Name"
              required
              ></v-text-field>
              <v-text-field
              v-model="ManageMenu.mprice"
              :counter="20"
              label="Menu Price"
              required
              ></v-text-field>
              <br>
              <v-combobox
              v-model="ManageMenu.CATEGORY_NAME"
              :items="MenuCategory"
              label="MenuCategory"
              multiple
              outlined
              dense
              ></v-combobox>
              <br>
              <v-combobox
              v-model="ManageMenu.INGREDIENT"
              :items="MenuIngredient"
              label="Menu Ingredient"
              multiple
              outlined
              dense
              ></v-combobox>
              <br>
              <v-combobox
              v-model="ManageMenu.TYPE"
              :items="MenuType"
              label="Menu Type"
              multiple
              outlined
              dense
              ></v-combobox>
              <br>
              <v-btn small >Add Menu</v-btn>
              <!-- @click="saveData" -->
            </v-col>
            <v-col cols = 7>
              <v-card
              class="mx-auto"
              max-width="550"
              outlined
              >
              <v-simple-table style = "center" class="blue lighten-5">
                <template v-slot:default>
                  <thead>
                    <tr>
                      <th class="text-left">Menu name</th>
                      <th class="text-left">Price</th>
                      <th class="text-left">Category</th>
                      <th class="text-left">Ingredient</th>
                      <th class="text-left">Type</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr v-for="item in showtable" :key="item.name">
                      <td class="text-center">{{ item.sel_patient.id }}</td>
                      <td>{{ item.sel_patient.name }}</td>
                      <td class="text-center">{{ item.sel_patient.room }}</td>
                      <td>{{ item.sel_menu.m_name }}</td>
                      <td class="text-center">{{ item.sel_time.time }}</td>
                    </tr>
                  </tbody>
                </template>
              </v-simple-table>
            </v-card>
          </v-col>
        </v-layout>
      </v-container>
    </v-col>
  </div>
</div>
</template>

<script>
import http from "../https";
export default {
  name:"ManageMenu",
  data(){
    return{
      ManageMenu:{
      },
      showtable:[],
      MenuCategory:[],
      MenuIngredient:[],
      MenuType:[]
    }
  },
  methods:{
    // saveData(){
    //   http.post("/manageMenus/"+this.manageMenus.MENU_CG.id+"/"+this.manageMenus.MENU_IGD.rec_id+"/"+this.manageMenus.MENU_TP.rec_id)
    //   .then(response => {
    //     // console.log(response);
    //     this.getDataTable();
    //   });
    //   this.submitted = true;
    // },
    getMenuType(){
      http.get("/menuTypes").then(response =>{
        this.MenuType = response.data;
      });
      this.submitted = true;

    },
    getMenuCategory(){

      http.get("/menuCategories").then(response => {
        this.MenuCategory = response.data;
      });
      this.submitted = true;
    },
    getMenuIngredient(){

      http.get("/menuIngredients").then(response =>{
        this.MenuIngredient = response.data;
      });
      this.submitted = true;
    },
    getDataTable(){
      http.get("/manageMenus").then(response =>{
        this.showtable = response.data;
      });
      this.submitted = true;
    }
  },
  mounted(){
    this.getMenuType();
    this.getMenuCategory();
    this.getMenuIngredient();
    this.getDataTable();
  }
}
</script>
