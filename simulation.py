import openrouteservice
from openrouteservice import convert
from geopy.distance import geodesic
import time
import datetime
import folium
import webbrowser


coords = ((5.482373,51.438115),(5.483191,51.438205))
AvgWalkingSpeed = .5
ArrivalRadius = 0.05
nextStep = True;
response = input("Please enter deliverer speed in m/s: ")
print (response)
c = int(response)
d = c*.07
print (str(d))
client = openrouteservice.Client(key='5b3ce3597851110001cf6248bc38a58bed6c482bb48e670dff6a3f85')

geometry = client.directions(coords)['routes'][0]['geometry']

decoded = convert.decode_polyline(geometry)


coordinates = decoded['coordinates']
print (range(len(coordinates)))
print (coordinates)
old_time = datetime.datetime.now()
totalDistance =  0;



for i in range(len(coordinates)-1):
	print (i)
	pathStr = "C:\\Users\\Damhuis\\Downloads\\Maps\\test"
	pathStr += ".html"
	tooltip = str(i)
	m = folium.Map(location=[coordinates[i][1],coordinates[i][0]],zoom_start=40)
	folium.Marker([coordinates[i][1],coordinates[i][0]], popup='<i>Mt. Hood Meadows</i>', tooltip=tooltip).add_to(m)
	m.save(pathStr)
	webbrowser.open('file://' + pathStr)
	traveledDistance = 0
	toTravelDistance = geodesic(coordinates[i], coordinates[i+1]).m
	travelString = ""
	while((toTravelDistance-traveledDistance)>0):
		traveledDistance += d
		travelString += "Walked "
		travelString += str(d)
		travelString += "m "
		travelString += "towards new point "
		travelString += str(coordinates[i+1])
		travelString += "from point "
		travelString += str(coordinates[i])
		travelString += "distance to next point is "
		travelString += str(toTravelDistance-traveledDistance)
		travelString += "m"
		print (travelString)
		travelString = ""
		time.sleep(.07)
	print (geodesic(coordinates[i], coordinates[i+1]).m)
	totalDistance += geodesic(coordinates[i], coordinates[i+1]).m
	print (totalDistance)
	print (coordinates[i][0])
	print (coordinates[i][1])
new_time = datetime.datetime.now()
diff = new_time - old_time
endstr = "trip of "
endstr += str(totalDistance)
endstr += "m took"
endstr += str(diff)
print (endstr)

