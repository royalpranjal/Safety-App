from rest_framework import serializers
from .models import LocationOfUser

class LocationOfUserSerializer(serializers.ModelSerializer):
	name = serializers.CharField(required=True)
	latitude = serializers.CharField(required=True)
	longitude = serializers.CharField(required=True)

	class Meta:
		model = LocationOfUser
		fields = ('name', 'latitude', 'longitude')