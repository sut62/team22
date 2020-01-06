<template>
  <div class="mainpage">
    <div class="outside">
      <br>
      <v-col cols = 12>
        <v-container>
          <v-layout row wrap>
            <v-col cols = 3>

              <v-text-field
              v-model="ManageMenu.MENU_NAME"
              label="Menu Name"
              :rules="[(v) => !!v || 'Item is required']"
              required
              outlined
              dense
              ></v-text-field>

              <v-text-field
              v-model="ManageMenu.MENU_PRICE"
              type="number"
              :rules="[(v) => !!v || 'Item is required']"
              label="Menu Price"
              required
              outlined
              dense
              ></v-text-field>

              <br>
              <v-combobox
              v-model="ManageMenu.CATE_ID"
              :items="MenuCate"
              label="Menu Category"
              item-text="cname"
              item-value="cid"
              multiple
              outlined
              dense
              ></v-combobox>

              <br>
              <v-combobox
              v-model="ManageMenu.INGRE_ID"
              :items="MenuIngre"
              label="Menu Ingredient"
              item-text="iname"
              item-value="id"
              multiple
              outlined
              dense
              ></v-combobox>

              <br>
              <v-combobox
              v-model="ManageMenu.TYPE_ID"
              :items="MenuType"
              label="Menu Type"
              item-text="tname"
              item-value="tid"
              multiple
              outlined
              dense
              ></v-combobox>

              <br>
              <v-btn small @click="saveData">Add Menu</v-btn>
              <!-- @click="saveData" -->
            </v-col>
            <v-col cols = 7>
              <v-card
              class="mx-auto"
              max-width="550"
              outlined
              >
              <v-simple-table style = "center" >
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
                      <td class="text-center">{{ item.sel_patient.name }}</td>
                      <td class="text-center">{{ item.sel_cate.cname }}</td>
                      <td class="text-center">{{ item.sel_ingre.iname }}</td>
                      <td class="text-center">{{ item.sel_type.tname }}</td>
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
      MENU_NAME:[],
      MENU_PRICE:[],
      MenuCate:[],
      MenuIngre:[],
      MenuType:[],
      showtable:[]
    }
  },
  methods:{
    saveData(){
      http.post("/ManageMenus/"+
      this.ManageMenu.MENU_NAME
      +"/"+
      this.ManageMenu.MENU_PRICE
      +"/"+
      this.ManageMenu.CATE_ID.cid
      +"/"+
      this.ManageMenu.INGRE_ID.id
      +"/"+
      this.ManageMenu.TYPE_ID.tid)
      .then(response => {
        console.log(response);
        alert("เพิ่มเมนูสำเร็จ")
        location.reload();
        this.getDataTable();

      })
      .catch(e => {
          console.log(e);
          alert("เพิ่มเมนูไม่สำเร็จ")
        })
      ;
      this.submitted = true;
    },

    getMenuCate(){

      http.get("/MenuCates").then(response => {
        this.MenuCate = response.data;
      });
      this.submitted = true;
    },
    getMenuIngre(){

      http.get("/MenuIngres").then(response =>{
        this.MenuIngre = response.data;
      });
      this.submitted = true;
    },
    getMenuType(){
      http.get("/MenuTypes").then(response =>{
        this.MenuType = response.data;
      });
      this.submitted = true;

    },
    getDataTable(){
      http.get("/ManageMenus").then(response =>{
        this.showtable = response.data;
      });
      this.submitted = true;
    }
  },
  mounted(){
    this.getMenuCate();
    this.getMenuIngre();
    this.getMenuType();
    this.getDataTable();
  }
}
</script>
