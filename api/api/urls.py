from django.conf.urls import include, url
from django.contrib import admin
from LocationAPI import views as locationView
from rest_framework.urlpatterns import format_suffix_patterns
from accounts import views as accountView

admin.autodiscover()

urlpatterns = [
    url(r'^admin/', admin.site.urls),
    url(r'^api/locationOfAllUsers/', locationView.UserList.as_view()),
    url(r'^api/locationOfAllUsers/users/(?P<pk>[0-9]+)/$', locationView.UserDetail.as_view()),
    url(r'^api/users/', include('accounts.urls')),	
    url(r'^api/users/individual/(?P<pk>[0-9]+)/$', accountView.UserDetail.as_view()),
    # url(r'^docs/', include('rest_framework_swagger.urls')),
]

urlpatterns = format_suffix_patterns(urlpatterns)