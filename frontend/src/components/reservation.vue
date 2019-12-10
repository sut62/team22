<template>
  <v-container class="mt-12 ml-12">
    <v-text-field
      v-model="reservation.customer"      
      label="Customer ID"  
      prepend-icon="mdi-human-male-female"
    ></v-text-field>
		<v-btn color="success"
      @click="customerchk"
    >Search</v-btn>
		<v-menu max-width="290" >
      <template v-slot:activator="{ on }">
        <v-text-field
        
        readonly
        label="Date"
        prepend-icon="mdi-calendar-range"
        :value="formattedDate"
        v-on="on"
      ></v-text-field>
      </template>
      <v-date-picker v-model="date" ></v-date-picker>
    </v-menu>
    <v-row class="ml-0">
        <v-menu
          ref="menu"
          v-model="menu1"
          :close-on-content-click="false"
          :nudge-right="40"
          lazy
          transition="scale-transition"
          offset-y
          max-width="290x"
          max-height="290x"
        >
          <template v-slot:activator="{on}"
          >
            <v-text-field
              v-model="reservation.time"
              v-on="on"
              label="Pick Time"
              prepend-icon="access_time"
              readlonly
              id="id"
            ></v-text-field>
            
          </template>
          <v-time-picker
              v-if="menu1"
              v-model="reservation.time"
              full-width
              @click:minute="$ref.menu.save(time)"
            ></v-time-picker>
        </v-menu>
     </v-row>
    <v-combobox
      label="Select Table"
      v-model="reservation.table"
      :items="table"
      prepend-icon="mdi-table-chair"
    >
    </v-combobox>
    <v-combobox
      label="Select Number of Seats"
      v-modle="reservation.seats"
      :items="seat"
      prepend-icon="mdi-seat"
    >
    </v-combobox>
    <v-combobox
      label="Select Service"
      v-modle="reservation.service"
      :items="service"
    >
    </v-combobox>
    <v-btn color="success"
      @click="save"
      
    >SAVE 
    <v-icon>mdi-floppy</v-icon>
    </v-btn>
  </v-container>
</template>

<script>
import format from 'date-fns/format'
import parselISO from 'date-fns/parseISO'
export default {
  name: 'HelloWorld',

  data: () => ({
        menu1:false,
        date:null,
        HH:['10','11','12','13','14','15','16','17','18','19','20','21','22'],
        MM:["00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59"],
        SS:["00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59"],
        reservation:{
          time:null,
          table:'',
          seats:null,
          customer:'',
          service:'',
        },
        service:null,
        
  }),
  computed:{
    formattedDate(){
      return this.date ? format(parselISO(this.date), 'do MMM YYY') : ''
    }
  },
  methods:{
    customerchk(){

    },
    save(){

    },
    getTables(){

    },
    
  }
   
}
</script>
