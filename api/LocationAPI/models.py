from django.db import models

class LocationOfUser(models.Model):
	name = models.CharField(max_length=30, unique=True, primary_key=True)
	latitude = models.DecimalField(max_digits=10, decimal_places=6)
	longitude = models.DecimalField(max_digits=10, decimal_places=6)

	def _str_(self):
		return self.name
