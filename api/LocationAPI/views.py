from django.shortcuts import render
from django.shortcuts import get_object_or_404
from .models import LocationOfUser
from .serializer import LocationOfUserSerializer
from rest_framework.views import APIView
from rest_framework import status 
from django.http import Http404
from rest_framework.response import Response

class UserList(APIView):
    def get(self, request):
        locationOfUsers = LocationOfUser.objects.all()
        locationOfUserSerializer = LocationOfUserSerializer(locationOfUsers, many=True)
        return Response(locationOfUserSerializer.data)

    # def get_object(self, request, name):
    #     try:
    #         return LocationOfUser.objects.get(name=name)
    #     except LocationOfUser.DoesNotExist:
    #         return Http404

    # def getIndividual(self, request, name, format=None):
    #     locationOfUsers = self.get_object(name)
    #     serializer = LocationOfUserSerializer(locationOfUsers)
    #     return Response(serializer.data)

    # def put(self, request, name):
    #     locationOfUsers = self.get_object(name)
    #     serializer = LocationOfUserSerializer(locationOfUsers, data=request.data)
    #     if serializer.is_valid():
    #         serializer.save()
    #         return Response(serializer.data)
    #     return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def post(self, request):
        serializer = LocationOfUserSerializer(data=request.data)
        if serializer.is_valid():
            check = serializer.save()
            if check :
                    return Response(status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

class UserDetail(APIView):
    def get_object(self, pk):
        try:
            return LocationOfUser.objects.get(pk=pk)
        except LocationOfUser.DoesNotExist:
            raise Http404

    def get(self, request, pk, format=None):
        locationOfUsers = self.get_object(pk)
        locationOfUsers = LocationOfUserSerializer(locationOfUsers)
        return Response(locationOfUsers.data)

    def put(self, request, pk, format=None):
        locationOfUsers = self.get_object(pk)
        serializer = LocationOfUserSerializer(locationOfUsers, data=request.DATA)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, pk, format=None):
        locationOfUsers = self.get_object(pk)
        locationOfUsers.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)