-- ----------------------------------------------------------------------
-- SQL data bulk transfer script generated by the MySQL Migration Toolkit
-- ----------------------------------------------------------------------

-- Disable foreign key checks
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;

INSERT INTO `AntiFrieze`.`Add`(`ADDCODE`, `DESC`)
VALUES ('100', 'IS THIS VEHICLE EQUIPPED WITH AIR CONDITIONING?'),
  ('1000', 'IS THIS VEHICLE EQUIPPED WITH HEAVY DUTY SUSPENSION?'),
  ('105', 'IS THE COMPRESSOR TOP MOUNTED ON THIS VEHICLE?'),
  ('110', 'IS THIS VEHICLE EQUIPPED WITH AFTERMARKET AIR CONDITIONING?'),
  ('1100', 'IS THIS VEHICLE EQUIPPED WITH FRONT WHEEL DRIVE?'),
  ('1110', 'ARE YOU REPLACING THE BOOT OR BOOTS?'),
  ('1150', 'IS THIS VEHICLE EQUIPPED WITH AN A-ARM TYPE SUSPENSION?'),
  ('1155', 'IS THIS VEHICLE EQUIPPED WITH SINGLE I-BEAM SUSPENSION?'),
  ('1160', 'ARE YOU REPLACING THE INNER?'),
  ('1163', 'IS THIS A ONE PIECE REAR MAIN SEAL?'),
  ('1165', 'ARE YOU GOING TO REPLACE THE FRONT SEAL OR SEALS?'),
  ('117', 'ARE YOU REPLACING THE REAR BLOWER MOTOR?'),
  ('1170', 'ARE YOU REPLACING JUST THE HUB?'),
  ('118', 'IS THE BLOWER MOTOR YOU ARE REPLACING UNDER THE SEAT?'),
  ('119', 'ARE YOU REPLACING THE AIR CONDITIONER BLOWER MOTOR?'),
  ('120', 'WOULD YOU LIKE TO INCLUDE A LEAK CHECK WITH THIS QUOTE?'),
  ('1200', 'IS THIS VEHICLE EQUIPPED WITH POWER WINDOWS?'),
  ('1210', 'IS THIS A FOUR DOOR VEHICLE?'),
  ('125', 'IS IT NECESSARY TO RECOVER OR RECYCLE THE FREON?'),
  ('1250', 'IS THIS VEHICLE A CONVERTIBLE?'),
  ('1255', 'IS THIS ASSEMBLY ELECTRICALLY CONTROLLED?'),
  ('1260', 'IS THIS VEHICLE EQUIPPED WITH A CENTER CONSOLE?'),
  ('130', 'WOULD YOU LIKE TO INCLUDE A FLUSH WITH THIS QUOTE?'),
  ('1300', 'IS THIS VEHICLE EQUIPPED WITH SKID CONTROL?'),
  ('131', 'WOULD YOU LIKE TO INCLUDE A FLUSH WITH THIS QUOTE?'),
  ('132', 'WOULD YOU LIKE TO INCLUDE A FLUSH WITH THIS QUOTE?'),
  ('133', 'WOULD YOU LIKE TO INCLUDE A FLUSH WITH THIS QUOTE?'),
  ('134', 'WOULD YOU LIKE TO INCLUDE A FLUSH WITH THIS QUOTE?'),
  ('135', 'WOULD YOU LIKE TO INCLUDE A FLUSH WITH THIS QUOTE?'),
  ('140', 'ARE YOU GOING TO REPLACE THE ACCUMULATOR OR DRIER?'),
  ('1400', 'IS THIS VEHICLE EQUIPPED WITH A TILT STEERING WHEEL?'),
  ('1410', 'IS IT TELESCOPIC?'),
  ('150', 'ARE YOU TESTING OBDII, EMISSIONS OR PERFORMANCE?'),
  ('1500', 'IS THIS VEHICLE EQUIPPED WITH AN AUTOMATIC TRANSMISSION?'),
  ('1501', 'ARE YOU GOING TO REPLACE THE TORQUE CONVERTER?'),
  ('1502', 'ARE YOU GOING TO REMOVE THE TRANSAXLE?'),
  ('1503', 'ARE YOU GOING TO REMOVE THE TRANSFER CASE?'),
  ('1510', 'IS THIS VEHICLE EQUIPPED WITH A 3 SPEED TRANSMISSION?'),
  ('1520', 'IS THIS VEHICLE EQUIPPED WITH A 4 SPEED TRANSMISSION?'),
  ('1530', 'IS THIS VEHICLE EQUIPPED WITH A 5 SPEED TRANSMISSION?'),
  ('1535', 'IS THIS VEHICLE EQUIPPED WITH A 6 SPEED TRANSMISSION?'),
  ('1537', 'IS THIS VEHICLE EQUIPPED WITH A ZF5 TRANSMISSION?'),
  ('1540', 'IS THIS TRANSMISSION EQUIPPED WITH OVERDRIVE?'),
  ('1541', 'IS THIS VEHICLE EQUIPPED WITH A 7 SPEED TRANSMISSION?'),
  ('1542', 'IS THIS VEHICLE EQUIPPED WITH A 13 SPEED TRANSMISSION?'),
  ('1549', 'IS THIS VEHICLE EQUIPPED WITH A POWER TAKE OFF?'),
  ('155', 'ARE YOU GOING TO RELACE THE FUEL FILTERS?'),
  ('1550', 'IS THIS VEHICLE EQUIPPED WITH A MANUAL TRANSMISSION?'),
  ('1553', 'IS THIS VEHICLE EQUIPPED WITH A BORG WARNER TRANSMISSION?'),
  ('1554', 'IS THIS VEHICLE EQUIPPED WITH AN (E40D) TRANSMISSION?'),
  ('1555', 'IS THIS VEHICLE EQUIPPED WITH A (TH440-T4 OR 4T60) TRANSMISSION?'),
  ('1556', 'IS THIS VEHICLE EQUIPPED WITH A (125 OR 3T40) TRANSMISSION?'),
  ('1558', 'IS THIS VEHICLE EQUIPPED WITH A (4L80E OR 4T80E) TRANSMISSION?'),
  ('1559', 'IS THIS VEHICLE EQUIPPED WITH A (700R4) TRANSMISSION?'),
  ('1560', 'IS THIS MODEL EQUIPPED WITH A FLOOR SHIFT?'),
  ('1565', 'IS IT NECESSARY TO REPACK THE WHEEL BEARINGS?'),
  ('1570', 'ARE YOU GOING TO REMOVE THE FLYWHEEL?'),
  ('1580', 'ARE YOU GOING TO REPLACE THE PILOT BEARING?'),
  ('1585', 'IS THIS ASSEMBLY INTERNALLY MOUNTED?'),
  ('1590', 'IS THIS ASSEMBLY EXTERNALLY MOUNTED?'),
  ('1600', 'IS THIS VEHICLE EQUIPPED WITH 4 WHEEL DRIVE?'),
  ('1610', 'IS THIS VEHICLE EQUIPPED WITH 4 WHEEL STEERING?'),
  ('1650', 'ARE YOU REPLACING ALL OF THE U-JOINTS?'),
  ('1700', 'IS IT NECESSARY TO REMOVE THE STARTER?'),
  ('1710', 'ARE ANY FREEZE PLUGS UNDER THE MOTOR MOUNTS?'),
  ('1720', 'ARE YOU REPLACING THE REAR MOTOR MOUNT?'),
  ('1730', 'IS THIS VEHICLE EQUIPPED WITH HYDRAULIC ENGINE MOUNTS?'),
  ('1800', 'DO YOU WANT YOUR QUOTE TO INCLUDE DIAGNOSIS & TESTING?'),
  ('1810', 'ARE SOLENOID TESTS NECESSARY?'),
  ('1820', 'DO YOU WANT TO INCLUDE A POWER MODULE DIAGNOSIS?'),
  ('1830', 'DO YOU WANT TO INCLUDE A PRESSURE TEST?'),
  ('1840', 'ARE YOU GOING TO OVERHAUL THIS ASSEMBLY?'),
  ('1850', 'IS THE FUEL FILTER LOCATED IN THE FUEL TANK?'),
  ('1855', 'IS THIS ASSEMBLY MOUNTED IN THE FUEL TANK?'),
  ('1920', 'DOES THIS INCLUDE A COMPRESSION TEST?'),
  ('1925', 'DOES THIS INCLUDE REPLACEMENT OF THE PLUG WIRES?'),
  ('1930', 'DO YOU WANT TO INCLUDE GEAR REPLACEMENT?'),
  ('200', 'IS THIS VEHICLE EQUIPPED WITH POWER STEERING?'),
  ('2000', 'IS THIS A DOUBLE OVERHEAD CAM ENGINE?'),
  ('2010', 'ARE YOU TRANSFERRING ANY PARTS FROM ANOTHER ENGINE?'),
  ('2020', 'IS THIS A 16 VALVE ENGINE?'),
  ('2040', 'DO YOU WANT TO INCLUDE A VALVE ADJUSTMENT?'),
  ('2050', 'ARE YOU GOING TO REMOVE THE ENGINE TO GAIN ACCESS?'),
  ('210', 'IS THIS VEHICLE EQUIPPED WITH A JAKE BRAKE?'),
  ('220', 'IS THIS VEHICLE EQUIPPED WITH A DUAL THERMACTOR?'),
  ('2500', 'IS THIS JOB IN ADDITION TO A TIMING BELT REPLACEMENT?'),
  ('2510', 'ARE YOU REPLACING THE TENSIONER?'),
  ('2520', 'ARE YOU REPLACING THE TIMING BELT?'),
  ('2530', 'ARE YOU REPLACING THE TIMING CHAIN?'),
  ('2600', 'WOULD YOU LIKE TO INCLUDE PRESSING THE BEARING?'),
  ('2610', 'ARE YOU GOING TO USE A BREAK OUT BOX?'),
  ('300', 'IS THIS VEHICLE EQUIPPED WITH AN AIR PUMP?'),
  ('3000', 'IS THE FAN CLUTCH THREADED ONTO THE WATER PUMP SHAFT?'),
  ('3010', 'ARE YOU REPLACING BOTH HOSES?'),
  ('3020', 'DOES THIS VEHICLE HAVE AN INTERCOOLER?'),
  ('3030', 'IS THIS VEHICLE EQUIPPED WITH AN OIL COOLER?'),
  ('3100', 'IS THIS CAR EQUIPPED WITH AUTOMATIC LEVELERS?'),
  ('3110', 'IS THIS VEHICLE EQUIPPED WITH INDEPENDENT REAR SUSPENSION?'),
  ('3120', 'IS THIS VEHICLE EQUIPPED WITH COIL SPRINGS?'),
  ('3125', 'IS THIS VEHICLE EQUIPPED WITH HENDRICKSON SPRINGS?'),
  ('3130', 'DOES THIS VEHICLE HAVE AN ACCESS PANEL?'),
  ('3140', 'IS THIS VEHICLE EQUIPPED WITH AIR SUSPENSION?'),
  ('3150', 'IS THIS VEHICLE EQUIPPED WITH FULL ACTIVE SUSPENSION?'),
  ('350', 'IS THIS VEHICLE EQUIPPED WITH A SERPENTINE BELT?'),
  ('400', 'IS THIS VEHICLE EQUIPPED WITH A SPLASH PAN OR SHIELD?'),
  ('4000', 'IS THIS VEHICLE EQUIPPED WITH DISC BRAKES?'),
  ('4010', 'IS IT NECESSARY TO REMOVE THE MASTER CYLINDER?'),
  ('4020', 'IS THIS AN ADDITIONAL JOB ADDED TO A BRAKE JOB?'),
  ('4025', 'BRAKE SHOES ARE NOT INCLUDED - ARE YOU REPLACING THEM NOW?'),
  ('4030', 'IS THIS VEHICLE EQUIPPED WITH HYDR0-MAX?'),
  ('4040', 'IS THIS VEHICLE EQUIPPED WITH TANDEM REAR WHEELS?'),
  ('4041', 'ARE YOU OVERHAULING THE FRONT DIFFERENTIAL OF A TANDEM?'),
  ('4050', 'IS THIS VEHICLE EQUIPPED WITH AIR BRAKES?'),
  ('4053', 'IS THIS VEHICLE EQUIPPED WITH A JAKE BRAKE?'),
  ('4055', 'IS THIS VEHICLE EQUIPPED WITH A BRAKESAVER?'),
  ('4060', 'IS THIS VEHICLE A CABOVER DESIGN?'),
  ('4065', 'IS THIS VEHICLE A CONVENTIONAL DESIGN?'),
  ('4070', 'IS THIS VEHICLE A SHORT CONVENTIONAL DESIGN?'),
  ('4080', 'IS THIS VEHICLE A TILT-CAB DESIGN?'),
  ('450', 'IS THIS VEHICLE EQUIPPED WITH AN AIR BAG?'),
  ('500', 'IS THIS VEHICLE EQUIPPED WITH AN AIR RECYCLE SYSTEM?'),
  ('5000', 'IS THIS VEHICLE A STATION WAGON OR HATCHBACK?'),
  ('600', 'IS THIS VEHICLE EQUIPPED WITH ANTI-LOCK BRAKES?'),
  ('6007', 'IS THIS VEHICLE EQUIPPED WITH DUAL EXHAUST?'),
  ('6010', 'DOES THIS INCLUDE R & R OF THE ALTERNATOR?'),
  ('6020', 'ARE YOU TESTING MULTIPLE CIRCUITS?'),
  ('6030', 'ARE YOU DOING A VOLTAGE DROP TEST?'),
  ('6040', 'ARE YOU TESTING CONTINUITY WITH AN OHM METER?'),
  ('6050', 'ARE YOU TESTING FOR SHORTS IN THE SYSTEM?'),
  ('6060', 'ARE YOU DIAGNOSING MULTIPLE COMPONENTS?'),
  ('6070', 'ARE YOU DOING CONTINUITY TESTS?'),
  ('6080', 'ARE YOU TRACING SHORTS?'),
  ('610', 'IS THIS VEHICLE EQUIPPED WITH TRACTION CONTROL?'),
  ('6100', 'IS IT NECESSARY TO REMOVE THE WIPER MOTOR?'),
  ('6200', 'IS THIS A POWER ANTENNA?'),
  ('6201', 'DOES THIS INCLUDE THE LEAD-IN TO THE RADIO?'),
  ('6300', 'IS THIS A TWO PIN PRESSURE SWITCH?'),
  ('700', 'IS THIS EQUIPPED WITH A BALANCE SHAFT & TWO TIMING BELTS?'),
  ('7000', 'IS THE BLOCK STILL IN THE VEHICLE?'),
  ('7010', 'ARE YOU REBORING TO OVERSIZE THE CYLINDERS?'),
  ('7020', 'ARE YOU USING AN ANGLE PLATE?'),
  ('7030', 'ARE YOU USING HEAT OR CUTTING TOOLS TO REMOVE THIS ITEM?'),
  ('7400', 'ARE YOU REPLACING BOTH SENSORS?'),
  ('7500', 'ARE YOU REPLACING BOTH SIDES?'),
  ('7501', 'ARE YOU REPLACING THE LEFT SIDE?'),
  ('7502', 'ARE YOU REPLACING THE RIGHT SIDE?'),
  ('7503', 'ARE YOU REPLACING THE REAR?'),
  ('7504', 'ARE YOU REPLACING THE FRONT?'),
  ('7510', 'ARE YOU REPLACING THE PASSENGER SIDE?'),
  ('7520', 'ARE YOU REPLACING THE DRIVER\'S SIDE?'),
  ('7530', 'ARE YOU REPLACING BOTH CABLES?'),
  ('7540', 'WOULD YOU LIKE TO INCLUDE TIME FOR TEARDOWN?'),
  ('7600', 'IS THIS ASSEMBLY RIVETED?'),
  ('7603', 'ARE THE CATALYTIC CONVERTERS MOUNTED ON THE MANIFOLD?'),
  ('7605', 'IS THIS VEHICLE EQUIPPED WITH A CATALYTIC CONVERTER?'),
  ('7607', 'ARE YOU GOING TO REPLACE BOTH MUFFLERS?'),
  ('7610', 'DOES THIS INCLUDE A RESONATOR?'),
  ('7615', 'IS IT NECESSARY TO REMOVE THE MANIFOLD?'),
  ('7620', 'IS STUD REPLACEMENT NECESSARY?'),
  ('7630', 'ARE YOU GOING TO DRILL & TAP A BROKEN STUD?'),
  ('7700', 'IS THIS A FIRST DESIGN ENGINE?'),
  ('7701', 'ARE THESE ROTORS SEPERABLE?'),
  ('800', 'IS THIS VEHICLE EQUIPPED WITH CRUISE CONTROL?'),
  ('8000', 'DO YOU WANT TO INCL. A 2 WHL ALIGNMENT WITH THIS QUOTE?'),
  ('8010', 'DO YOU WANT TO INCL. A 4 WHL ALIGNMENT WITH THIS QUOTE?'),
  ('8015', 'IS A CAM KIT REPLACEMENT NECESSARY?'),
  ('8020', 'IF EQUIPPED WITH 4 ASSEMBLIES, ARE YOU REPLACING ALL FOUR?'),
  ('8090', 'ARE YOU REPLACING BOTH ASSEMBLIES?'),
  ('8091', 'ARE YOU REPLACING BOTH JOINTS ON THIS SIDE?'),
  ('8100', 'ARE YOU REPLACING MORE THAN ONE?'),
  ('8110', 'ARE YOU REPLACING THE TIE BOLTS?'),
  ('8120', 'ARE YOU REPLACING A SINGLE SWITCH?'),
  ('8300', 'DOES THIS VEHICLE HAVE FOG LIGHTS?'),
  ('8500', 'IS THIS A FULL-FLOATING ASSEMBLY?'),
  ('900', 'IS THIS VEHICLE EQUIPPED WITH LOCKING WHEEL COVERS?'),
  ('9000', 'ARE YOU ADJUSTING AIR-FUEL MIXTURE?'),
  ('9010', 'IS THIS AN INTERNAL ADJUSTMENT TO THE CARBURETOR?'),
  ('9020', 'IS THIS A 4 BARREL?'),
  ('9035', 'IS THIS A TWO PIECE ASSEMBLY?'),
  ('9040', 'ARE YOU REPLACING BOTH THE FRONT AND REAR?'),
  ('9050', 'ARE YOU REPLACING THE UPPER ALSO?'),
  ('9051', 'ARE YOU OVERHAULING BOTH CARBURETORS?'),
  ('9060', 'IS THIS A VARIABLE VENTURI CARBURETOR?'),
  ('9065', 'IS THIS A VARA-JET CARBURETOR?'),
  ('9070', 'IS THIS A HOLLEY CARBURETOR?'),
  ('9100', 'WOULD YOU LIKE TO INCLUDE AN EVACUATION AND RECHARGE?'),
  ('9101', 'WOULD YOU LIKE TO INCLUDE AN EVACUATION AND RECHARGE?'),
  ('9102', 'WOULD YOU LIKE TO INCLUDE AN EVACUATION AND RECHARGE?'),
  ('9103', 'WOULD YOU LIKE TO INCLUDE AN EVACUATION AND RECHARGE?'),
  ('9104', 'WOULD YOU LIKE TO INCLUDE AN EVACUATION AND RECHARGE?'),
  ('9105', 'WOULD YOU LIKE TO INCLUDE AN EVACUATION AND RECHARGE?'),
  ('9106', 'WOULD YOU LIKE TO INCLUDE AN EVACUATION AND RECHARGE?'),
  ('9107', 'WOULD YOU LIKE TO INCLUDE AN EVACUATION AND RECHARGE?'),
  ('9108', 'WOULD YOU LIKE TO INCLUDE AN EVACUATION AND RECHARGE?'),
  ('9109', 'WOULD YOU LIKE TO INCLUDE AN EVACUATION AND RECHARGE?'),
  ('9110', 'WOULD YOU LIKE TO INCLUDE AN EVACUATION AND RECHARGE?'),
  ('9111', 'WOULD YOU LIKE TO INCLUDE AN EVACUATION AND RECHARGE?'),
  ('9112', 'WOULD YOU LIKE TO INCLUDE AN EVACUATION AND RECHARGE?'),
  ('9113', 'WOULD YOU LIKE TO INCLUDE AN EVACUATION AND RECHARGE?'),
  ('9114', 'WOULD YOU LIKE TO INCLUDE AN EVACUATION AND RECHARGE?'),
  ('9115', 'WOULD YOU LIKE TO INCLUDE AN EVACUATION AND RECHARGE?'),
  ('9116', 'WOULD YOU LIKE TO INCLUDE AN EVACUATION AND RECHARGE?'),
  ('9117', 'WOULD YOU LIKE TO INCLUDE AN EVACUATION AND RECHARGE?'),
  ('9118', 'WOULD YOU LIKE TO INCLUDE AN EVACUATION AND RECHARGE?'),
  ('9119', 'WOULD YOU LIKE TO INCLUDE AN EVACUATION AND RECHARGE?'),
  ('9121', 'WOULD YOU LIKE TO INCLUDE AN EVACUATION AND RECHARGE?'),
  ('9140', 'IS THIS VEHICLE EQUIPPED WITH DUAL REAR WHEELS?'),
  ('9145', 'ARE YOU REPLACING THE EVAPORATOR SENSOR?'),
  ('9147', 'IS THIS VEHICLE EQUIPPED WITH A MARK III A/C UNIT?'),
  ('9150', 'ARE YOU REPLACING THE EXPANSION TUBE OR VALVE?'),
  ('9700', 'IS THE CLOCK PART OF A MULTIFUNCTIONAL UNIT?'),
  ('9710', 'IS THE ASSEMBLY MOUNTED IN THE STEERING COLUMN?'),
  ('9720', 'IS THIS VEHICLE EQUIPPED WITH NON-SEPARABLE ROTORS?'),
  ('9730', 'IS THIS VEHICLE EQUIPPED WITH AN ELECTRONIC CLUSTER?'),
  ('9735', 'ARE YOU REMOVING THE DASH BECAUSE THE AIR BAG DEPLOYED?'),
  ('9740', 'IS IT NECESSARY TO REMOVE THE SPEEDOMETER HEAD?'),
  ('9750', 'IS THIS VEHICLE EQUIPPED WITH AN ANTI-THEFT SYSTEM?'),
  ('9780', 'IS THIS VEHICLE EQUIPPED WITH AUTOMATIC TEMPERATURE CONTROL?'),
  ('9800', 'IS THIS AN FX MODEL?'),
  ('9801', 'ARE YOU REMOVING THE LOWER CONTROL ARMS TO GAIN ACCESS?'),
  ('9900', 'IS THE YOKE BOLTED ON?'),
  ('9901', 'ARE YOU GOING TO REMOVE THE ACCUMULATOR & ORIFICE?'),
  ('9902', 'IS THIS TRUCK GVW 8600 OR LARGER?'),
  ('9903', 'IS THIS VEHICLE EQUIPPED WITH A BUTTERFLY HOOD?'),
  ('9904', 'IS THIS VEHICLE EQUIPPED WITH AN ALLIGATOR HOOD?'),
  ('1167', 'ARE YOU GOING TO REPLACE THE BALANCE SHAFT SEAL?'),
  ('1620', 'ARE YOU REPLACING ALL OF THE ASSEMBLIES?'),
  ('7401', 'ARE YOU GOING TO REPLACE BOTH DISTRIBUTOR CAPS?'),
  ('7402', 'ARE YOU GOING TO REPLACE BOTH DISTRIBUTOR ROTORS?'),
  ('8021', 'ARE YOU REPLACING ALL 4 CAMSHAFTS?'),
  ('9905', 'ARE YOU REPLACING THE STEERING ARM?'),
  ('9906', 'IS THIS TRUCK EQUIPPED WITH CAST SPOKE WHEELS?'),
  ('9907', 'IS THIS TRUCK EQUIPPED WITH HENDRICKSON REAR SUSPESION?'),
  ('9908', 'IS THE TRANSMISSION A 5 OR 6 SPEED FULLER?'),
  ('9909', 'IS IT NECESSARY TO REMOVE THE EXHAUST PIPE?'),
  ('9910', 'ARE YOU REPLACING THE ADJUSTING ARMS?'),
  ('9911', 'ARE YOU DRILLING OUT THE SPOT WELDS?'),
  ('9912', 'DO YOU WANT TO INCLUDE TIME TO DRAIN THE TANK?'),
  ('9915', 'DO YOU WANT TO INCLUDE TIME TO REMOVE THE CARPET?'),
  ('9913', 'ARE YOU REPLACING THE INNER BEARING RACE?'),
  ('9914', 'ARE YOU REPLACING THE REAR TURBO MOUNTING MANIFOLD?'),
  ('9950', 'IS THIS VEHICLE EQUIPPED WITH AN AIR COMPRESSOR?'),
  ('9951', 'IS THIS VEHICLE EQUIPPED WITH A VACUUM PUMP?'),
  ('9952', 'ARE YOU GOING TO REPLACE THE FAN HUB?'),
  ('9953', 'ARE YOU GOING TO REPLACE THE HUB?'),
  ('9954', 'ARE YOU GOING TO REPLACE THE HANGERS?'),
  ('9955', 'IS THIS VEHICLE EQUIPPED WITH LUCAS - GIRLING BRAKES?'),
  ('9956', 'ARE YOU GOING TO REPLACE THE BEARINGS & SEALS?'),
  ('9957', 'ARE YOU REPLACING BOTH CALIPERS ON THIS SIDE?'),
  ('9958', 'ARE YOU REMOVING THE DRUMS FROM THE HUBS?'),
  ('9959', 'IS THIS VEHICLE EQUIPPED WITH DUAL EVAPORATORS?'),
  ('9972', 'ARE YOU GOING TO INSTALL A FACTORY RETROFIT KIT?'),
  ('1588', 'ARE YOU GOING TO REPLACE THE SLAVE CYLINDER?'),
  ('9960', 'IS THIS VEHICLE EQUIPPED WITH EQUALIZER BEAM SUSPENSION?'),
  ('9961', 'ARE YOU REPLACING ALL OF THE TRANSMISSION MOUNTS?'),
  ('9962', 'ARE YOU GOING TO REPLACE THE HOUSING?'),
  ('9963', 'IS THIS A FULL POWER ASSEMBLY?'),
  ('9964', 'ARE YOU REPLACING THE AUXILIARY TRANSMISSION PTO?'),
  ('9965', 'IS THIS VEHICLE EQUIPPED WITH A PASSLOCK II SYSTEM?'),
  ('9966', 'IS THIS A DUAL CHAMBER ASSEMBLY?'),
  ('9967', 'ARE THE DRUMS INBOARD MOUNTED?'),
  ('9969', 'IS THIS A FRONT OR REAR SERVICE TANK?'),
  ('9970', 'ARE YOU GOING TO REPLACE THE DISTRIBUTOR CAP OR ROTOR?'),
  ('9968', 'IS THIS AN INSIDE MOUNTED TANK?'),
  ('9971', 'IS THIS VEHICLE EQUIPPED WITH RAIL SLIDE CALIPERS?'),
  ('9981', 'IS THIS VEHICLE EQUIPPED WITH A SLEEPER?'),
  ('9982', 'IS THIS VEHICLE EQUIPPED WITH A TILT HOOD?'),
  ('9983', 'IS THIS VEHICLE EQUIPPED WITH A BELT DRIVE COMPRESSOR?'),
  ('9984', 'IS THIS DIFFERENTIAL EQUIPPED WITH AN ALUMINUM HOUSING?'),
  ('9973', 'IS THIS VEHICLE EQUIPPED WITH A STEEL FUEL TANK?'),
  ('9974', 'ARE YOU GOING TO REPLACE THE LOWER COVER?'),
  ('3021', 'IS THE TURBO INTERCOOLER AIR COOLED?'),
  ('9975', 'ARE YOU GOING TO CHANGE THE TRANSMISSION FLUID & FILTER?'),
  ('9976', 'ARE YOU GOING TO CHANGE THE DIFFERENTIAL FLUID?'),
  ('9200', 'ARE YOU REPLACING THE INNER COVER?'),
  ('9210', 'IS THE TRANSMISSION EQUIPPED WITH TWIN COUNTERSHAFTS?'),
  ('3040', 'IS THIS VEHICLE EQUIPPED WITH CALIFORNIA EMMISSIONS?'),
  ('9985', 'IS THIS AN OLD STYLE TRUCK?'),
  ('9986', 'IS THIS A 3500 SERIES PICKUP?'),
  ('1591', 'IS THIS VEHICLE EQUIPPED WITH A 4L60E TRANSMISSION?'),
  ('1805', 'DO YOU WANT TO ADD TIME FOR REPROGRAMING THE COMPUTER?'),
  ('9979', 'IS THIS AN 8 STUD WHEEL?'),
  ('7601', 'IS THIS ASSEMBLY PRESSED?'),
  ('9987', 'ARE YOU GOING TO REMOVE THE WHEEL STUDS?'),
  ('9988', 'IS THIS A NEW STYLE TRUCK?'),
  ('9989', 'IS THIS VEHICLE EQUIPPED WITH AN INTREGRAL CARRIER?'),
  ('9990', 'IS THIS A SPICER DANA DIFFERENTIAL?'),
  ('9991', 'IS THIS VEHICLE EQUIPPED WITH ELECTRIC FANS?'),
  ('905', 'IS THIS VEHICLE A PRE-RUNNER MODEL?'),
  ('605', 'IS THIS VEHICLE EQUIPPED WITH AN AUTO DISCONNECT DIFFERENTIAL?'),
  ('1175', 'ARE YOU REPLACING THE INTERMEDIATE SHAFT?'),
  ('7702', 'ARE THESE ROTORS OUTBOARD MOUNTED?'),
  ('1715', 'IS THIS FREEZE PLUG LOCATED AT THE BOTTOM OF THE INTAKE?'),
  ('7505', 'ARE YOU REPLACING THE INTAKE SIDE?'),
  ('9802', 'IS THIS AN SLS MODEL?'),
  ('650', 'IS CORROSION OR RUST A PROBLEM WITH THIS JOB?'),
  ('122', 'WOULD YOU LIKE TO ADD TIME FOR BLEEDING THE COOLING SYSTEM?'),
  ('8130', 'WOULD YOU LIKE TO ADD TIME TO PAINT THE NEW ASSEMBLY?'),
  ('1220', 'IS THIS VEHICLE EQUIPPED WITH ADJUSTABLE PEDALS?'),
  ('2525', 'IS THE TIMING CHAIN BROKEN?'),
  ('4075', 'IS THIS A MEDIUM CONVENTIONAL DESIGN?'),
  ('9220', 'ARE YOU GOING TO REMOVE THE STEERING RACK?'),
  ('1205', 'IS THIS VEHICLE EQUIPPED WITH POWER DOOR LOCKS?'),
  ('9803', 'ARE YOU REPLACING THE PLUGS WITH NEW DESIGN SPARK PLUGS?'),
  ('660', 'WOULD YOU LIKE TO ADD TIME FOR CLEANUP OF CONTAMINATION?'),
  ('910', 'IS THIS VEHICLE A COBRA?'),
  ('3035', 'IS THIS VEHICLE EQUIPPED WITH A POWER STEERING COOLER?');

INSERT INTO `AntiFrieze`.`JobLvl1`(`Lvl1Code`, `Lvl2Code`, `JobDesc`)
VALUES ('00', '00', 'ADJUSTMENTS'),
  ('00', '01', 'CAR SYMPTOMS-ENGINE PERFORMANCE'),
  ('00', '02', '                          STARTING - LIGHTS'),
  ('00', '03', '                          NOISES - COMFORTS'),
  ('00', '04', '                          VIBRATIONS - LEAKS'),
  ('00', '05', 'CHECK OUTS'),
  ('00', '06', 'COMMENTS W/O PRICES'),
  ('00', '07', 'DIAGNOSTIC CHARGES'),
  ('00', '08', 'MILEAGE SERVICES'),
  ('00', '09', 'ROAD SERVICES & TOWING'),
  ('01', '00', 'AIR CONDITIONING SERVICES'),
  ('01', '01', 'ALIGNMENT SERVICES'),
  ('01', '02', 'BATTERY - STARTING & CHARGING'),
  ('01', '03', 'BELTS & HOSES'),
  ('01', '04', 'BRAKE SERVICES'),
  ('01', '05', 'CAB & SLEEPER SERVICES (HEAVY DUTY)'),
  ('01', '06', 'COOLING SYSTEM SERVICES'),
  ('01', '07', 'IGNITION'),
  ('01', '08', 'LIGHTS & SIGNALS'),
  ('01', '09', 'LUBRICATION & FILTER'),
  ('01', '10', 'MACHINE OPERATIONS'),
  ('01', '11', 'MILEAGE SERVICES'),
  ('01', '12', 'MISCELLANEOUS & ACCESSORIES'),
  ('01', '13', 'TIRE & WHEEL SERVICES'),
  ('01', '14', 'TRANSMISSION SERVICES'),
  ('01', '15', 'TUNE-UP & MAINTENANCE'),
  ('01', '16', 'QUICK JOB COMBINATIONS'),
  ('02', '00', 'ANTI LOCK CONTROLS - HYDRAULICS'),
  ('02', '01', 'ANTI LOCK - WHEEL UNITS'),
  ('02', '02', 'BRAKE CONTROLS'),
  ('02', '03', 'EMERGENCY BRAKE'),
  ('02', '04', 'FRONT BRAKE SERVICES'),
  ('02', '05', 'HARDWARE'),
  ('02', '06', 'MASTER CYLINDER - HYDRAULICS'),
  ('02', '07', 'REAR BRAKE SERVICES'),
  ('02', '08', 'TIRE & WHEEL SERVICES'),
  ('02', '09', 'TEST - ADJUST - DIAGNOSE - SERVICE'),
  ('02', '10', 'QUICK JOB COMBINATIONS'),
  ('03', '00', 'AIR CONDITIONING'),
  ('03', '01', 'CAB & SLEEPER SERVICES (HEAVY DUTY)'),
  ('03', '02', 'BELTS'),
  ('03', '03', 'HOSES'),
  ('03', '04', 'ELECTRICAL - FANS - BLOWER MTRS'),
  ('03', '05', 'HEATER'),
  ('03', '06', 'RADIATOR - FAN CLUTCH - FAN'),
  ('03', '07', 'TEST - ADJUST - DIAGNOSE - SERVICE'),
  ('03', '08', 'THERMOSTAT - WATER PUMP'),
  ('03', '09', 'QUICK JOB COMBINATIONS'),
  ('04', '00', 'AIR SUSPENSION SYSTEM'),
  ('04', '01', 'CONTROL MODULES & COMPUTERS'),
  ('04', '02', 'FUEL INJECTION'),
  ('04', '03', 'IDLE SPEED CONTROLS'),
  ('04', '04', 'IGNITION'),
  ('04', '05', 'RELAYS'),
  ('04', '06', 'RIDE CONTROL & BODY CONTROL MODULE'),
  ('04', '07', 'SENSORS & SENDERS'),
  ('04', '08', 'SOLENOIDS & ACTUATORS'),
  ('04', '09', 'SWITCHES'),
  ('04', '10', 'VACUUM VALVES & SWITCHES'),
  ('04', '11', 'TEST - ADJUST - DIAGNOSE - SERVICE'),
  ('05', '00', 'CLUTCH'),
  ('05', '01', 'DIFFERENTIAL (REAR)'),
  ('05', '02', 'DIFFERENTIAL (FRONT) & TRANSAXLE'),
  ('05', '03', 'DRIVE SHAFT & U JOINTS'),
  ('05', '04', 'FRONT AXLES & SHAFTS'),
  ('05', '05', 'REAR AXLES'),
  ('05', '06', 'REAR SUSPENSION'),
  ('05', '07', 'TRANSMISSION - TRANSFER CASE'),
  ('05', '08', 'TRANSMISSION LINKAGE'),
  ('05', '09', 'TEST - ADJUST - DIAGNOSE - SERVICE'),
  ('06', '00', 'BATTERY & STARTING SYSTEM'),
  ('06', '01', 'CHARGING SYSTEM'),
  ('06', '02', 'CONTROL SWITCHES'),
  ('06', '03', 'ELECTRICAL FANS & BLOWER MTRS'),
  ('06', '04', 'ELECTRONIC DASH - GAUGES'),
  ('06', '05', 'FUSE BOX - FUSES - WIRING'),
  ('06', '06', 'IGNITION'),
  ('06', '07', 'LIGHTS & TURN SIGNALS'),
  ('06', '08', 'RELAYS & SENDERS'),
  ('06', '09', 'TEST - ADJUST - DIAGNOSE - SERVICE'),
  ('07', '00', 'CYLINDER HEAD & VALVE TRAIN'),
  ('07', '01', 'ENGINE - FRONT (TIMING CHAIN,ETC.)'),
  ('07', '02', 'ENGINE - LOWER HALF (OIL PAN,ETC.)'),
  ('07', '03', 'ENGINE - INTERNAL RELATED REPAIRS'),
  ('07', '04', 'ENGINE - OVERHAUL - R & R'),
  ('07', '05', 'ENGINE - REAR (MOUNTS, FILTER, ETC.)'),
  ('07', '06', 'FILTERS'),
  ('07', '07', 'FUEL INJECTION'),
  ('07', '08', 'GASKETS & SEALS'),
  ('07', '09', 'IGNITION'),
  ('07', '10', 'MACHINE OPERATIONS'),
  ('07', '11', 'TEST - ADJUST - DIAGNOSE - SERVICE'),
  ('07', '12', 'TURBO - SUPERCHARGER'),
  ('07', '13', 'QUICK JOB COMBINATIONS'),
  ('08', '00', 'AIR MGMT SYSTEM - AIR PUMP TYPE'),
  ('08', '01', 'COMPUTER CONTROLS'),
  ('08', '02', 'EGR SYSTEM'),
  ('08', '03', 'EVAPORATIVE - EMISSIONS - PCV'),
  ('08', '04', 'EXHAUST'),
  ('08', '05', 'FUEL CONTROLLERS - INTAKE MANIFOLD'),
  ('08', '06', 'SENSORS'),
  ('08', '07', 'TEST - ADJUST - DIAGNOSE - SERVICE'),
  ('08', '08', 'QUICK JOB COMBINATIONS'),
  ('09', '00', 'AIR SUSPENSION,RIDE CONTROL & BCM'),
  ('09', '01', 'ALIGNMENT SERVICES'),
  ('09', '02', 'BALL JOINTS & STRUTS'),
  ('09', '03', 'CAB & SLEEPER SERVICES (HEAVY DUTY)'),
  ('09', '04', 'CONTROL ARMS'),
  ('09', '05', 'DIFFERENTIAL AND TRANSAXLE'),
  ('09', '06', 'FRONT AXLES & SHAFTS'),
  ('09', '07', 'FRONT SUSPENSION'),
  ('09', '08', 'POWER STEERING - PUMP - HOSES'),
  ('09', '09', 'REAR AXLES'),
  ('09', '10', 'REAR SUSPENSION'),
  ('09', '11', 'SPRINGS - SHOCKS - STRUTS'),
  ('09', '12', 'STEERING GEAR - RACK & PINION'),
  ('09', '13', 'STEERING COLUMN'),
  ('09', '14', 'STEERING LINKAGE - STABILIZERS'),
  ('09', '15', 'TIRE & WHEEL SERVICES'),
  ('09', '16', 'TEST - ADJUST - DIAGNOSE - SERVICE'),
  ('10', '00', 'CARBURETOR - INTAKE MANIFOLD'),
  ('10', '01', 'CARBURETOR CONTROLS'),
  ('10', '02', 'IGNITION'),
  ('10', '03', 'ELECTRONIC MODULES'),
  ('10', '04', 'FUEL INJECTION'),
  ('10', '05', 'FUEL PUMP - TANK - FILTERS - LINES'),
  ('10', '06', 'SENSORS'),
  ('10', '07', 'TUNE-UP'),
  ('10', '08', 'TURBO - SUPERCHARGER'),
  ('10', '09', 'TEST - ADJUST - DIAGNOSE - SERVICE'),
  ('11', '00', 'ENGINE      - GASKETS & SEALS'),
  ('11', '01', 'FRONT END   - GASKETS & SEALS'),
  ('11', '02', 'REAR END    - GASKETS & SEALS'),
  ('11', '03', 'TRANS AXLE  - GASKETS & SEALS'),
  ('11', '04', 'TRANSMISSION- GASKETS & SEALS'),
  ('11', '05', 'TEST - ADJUST - DIAGNOSE - SERVICE'),
  ('12', '00', 'ACCUMULATOR - REC DRIER -  ORIFICES'),
  ('12', '01', 'BELTS - HOSES - O RING SEALS'),
  ('12', '02', 'CAB & SLEEPER SERVICES (HEAVY DUTY)'),
  ('12', '03', 'COMPRESSOR & CLUTCH'),
  ('12', '04', 'CONTROLLERS - HEATER CONTROL VALVE'),
  ('12', '05', 'EVAPORATOR - CONDENSOR - FAN'),
  ('12', '06', 'HEATER - BLOWER MOTORS - VENT DOORS'),
  ('12', '07', 'SWITCHS & RELAYS'),
  ('12', '08', 'TEST - ADJUST - DIAGNOSE - SERVICE'),
  ('12', '09', 'QUICK JOB COMBINATIONS'),
  ('13', '00', 'ALARMS,BUZZERS & HORN'),
  ('13', '01', 'CAB & SLEEPER SERVICES (HEAVY DUTY)'),
  ('13', '02', 'CABLES'),
  ('13', '03', 'CRUISE CONTROL'),
  ('13', '04', 'DASH - CLOCK - RADIO'),
  ('13', '05', 'DOORS - LATCHES & LOCKS'),
  ('13', '06', 'ELECTRONIC DASH - GAUGES'),
  ('13', '07', 'LIGHTS & SIGNALS'),
  ('13', '08', 'SEATS & SAFETY DEVICES'),
  ('13', '09', 'SPEEDOMETER & ODOMETER'),
  ('13', '10', 'SUN ROOF & CONVERTIBLE TOP'),
  ('13', '11', 'WINDOWS & MIRRORS'),
  ('13', '12', 'WINDSHIELD WIPERS'),
  ('13', '13', 'TEST - ADJUST - DIAGNOSE - SERVICE'),
  ('13', '14', 'UTILITY - CHERRY PICKER (PRENTICE 120 C-E)'),
  ('13', '15', 'WASTE MGMT - AUTO REACH LOADER (McNEILUS)'),
  ('13', '16', 'WASTE MGMT - REAR STEER LOADER (PETERSON)'),
  ('13', '17', 'WASTE MGMT - REAR SPLIT LOADER (HEIL)'),
  ('13', '18', 'WASTE MGMT - REAR SPLIT LOADER (LEACH)'),
  ('13', '19', 'WASTE MGMT - REAR SPLIT LOADER (PACK MOR)'),
  ('14', '00', 'CONTROL SWITCHES'),
  ('14', '01', 'DASH REPAIRS'),
  ('14', '02', 'ELECTRONIC DASH'),
  ('14', '03', 'GAUGES'),
  ('14', '04', 'LIGHT SWITCHES'),
  ('14', '05', 'SENDERS'),
  ('14', '06', 'SPEEDOMETER & ODOMETER'),
  ('14', '07', 'STEERING COLUMN'),
  ('14', '08', 'TEST - ADJUST - DIAGNOSE - SERVICE'),
  ('15', '00', 'AUTOMATIC & TRANS-AXLES'),
  ('15', '01', 'CLUTCH'),
  ('15', '02', 'CONTROLS'),
  ('15', '03', 'LINKAGE'),
  ('15', '04', 'MANUAL TRANS & TRANS-AXLES'),
  ('15', '05', 'TEST - ADJUST - DIAGNOSE - SERVICE');

INSERT INTO `AntiFrieze`.`Make`(`makeid`, `LVL12`, `MAKE`)
VALUES (1, '0700', 'ACURA'),
  (2, '0701', 'ALFA ROMEO'),
  (3, '0100', 'AMC'),
  (4, '0702', 'AUDI'),
  (5, '0800', 'BMW'),
  (6, '0400', 'BUICK'),
  (7, '0401', 'CADILLAC'),
  (8, '0402', 'CHEVROLET'),
  (9, '0007', 'CHEVROLET HD TRUCK'),
  (10, '0200', 'CHRYSLER'),
  (11, '0802', 'DAIHATSU'),
  (12, '0201', 'DODGE'),
  (13, '0103', 'EAGLE'),
  (14, '0803', 'FIAT'),
  (15, '0300', 'FORD'),
  (16, '0005', 'FORD HD TRUCK'),
  (17, '0006', 'FREIGHTLINER'),
  (18, '0003', 'GMC - LIGHT TRUCK'),
  (19, '0007', 'GMC HD TRUCK'),
  (20, '0900', 'HONDA'),
  (21, '0901', 'HYUNDAI'),
  (22, '0902', 'INFINITI'),
  (24, '0009', 'INTERNATIONAL'),
  (25, '1000', 'ISUZU'),
  (26, '0010', 'ISUZU HD TRUCK'),
  (27, '1001', 'JAGUAR'),
  (28, '0102', 'JEEP'),
  (29, '0011', 'KENWORTH'),
  (30, '1002', 'KIA'),
  (31, '0604', 'LAND ROVER'),
  (32, '1003', 'LEXUS'),
  (33, '0301', 'LINCOLN'),
  (34, '0012', 'MACK'),
  (35, '1101', 'MAZDA'),
  (36, '1102', 'MERCEDES'),
  (37, '0302', 'MERCURY'),
  (38, '1100', 'MG'),
  (39, '1200', 'MITSUBISHI'),
  (40, '0013', 'MITSUBISHI HD TRUCK'),
  (41, '1201', 'NISSAN'),
  (42, '0014', 'NISSAN HD TRUCK'),
  (43, '0500', 'OLDSMOBILE'),
  (44, '1202', 'OPEL'),
  (45, '0015', 'PETERBILT'),
  (46, '1300', 'PEUGEOT'),
  (47, '0202', 'PLYMOUTH'),
  (48, '0501', 'PONTIAC'),
  (49, '1301', 'PORSCHE'),
  (50, '0101', 'RENAULT'),
  (51, '1400', 'SAAB'),
  (52, '0502', 'SATURN'),
  (53, '1401', 'STERLING'),
  (54, '1402', 'SUBARU'),
  (55, '1500', 'SUZUKI'),
  (56, '1501', 'TOYOTA'),
  (57, '1502', 'TRIUMPH'),
  (58, '1600', 'VOLKSWAGEN'),
  (59, '1601', 'VOLVO'),
  (60, '0017', 'VOLVO HD TRUCK'),
  (61, '0019', 'WHITE'),
  (62, '1602', 'YUGO'),
  (63, '0801', 'DAEWOO'),
  (64, '0008', 'HINO'),
  (65, '0018', 'WESTERN STAR'),
  (67, '0016', 'SPARTAN CHASSIS');

INSERT INTO `AntiFrieze`.`Year`(`Year`)
VALUES ('1968'),
  ('1969'),
  ('1970'),
  ('1971'),
  ('1972'),
  ('1973'),
  ('1974'),
  ('1975'),
  ('1976'),
  ('1977'),
  ('1978'),
  ('1979'),
  ('1980'),
  ('1981'),
  ('1982'),
  ('1983'),
  ('1984'),
  ('1985'),
  ('1986'),
  ('1987'),
  ('1988'),
  ('1989'),
  ('1990'),
  ('1991'),
  ('1992'),
  ('1993'),
  ('1994'),
  ('1995'),
  ('1996'),
  ('1997'),
  ('1998'),
  ('1999'),
  ('2000'),
  ('2001'),
  ('2002'),
  ('2003'),
  ('2004'),
  ('2005'),
  ('2006'),
  ('2007'),
  ('2008'),
  ('2009');

-- Re-enable foreign key checks
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;

-- End of script
