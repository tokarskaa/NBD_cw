import riak

print "start"

myClient = riak.RiakClient()

print "client created"

myBucket = myClient.bucket('iceCreamBucket')

print "bucket created"

val1 = {"flavour": "strawberry", "scoops": 2}
key1 = myBucket.new('bestFlavour', data=val1)
key1.store()

print "document stored"

fetched = myBucket.get('bestFlavour')
print "document: ", fetched.data

fetched.data["scoops"] = 3
fetched.store()
print "document changed"

fetchedAfterUpdate = myBucket.get('bestFlavour')
print "document: ", fetchedAfterUpdate.data

fetchedAfterUpdate.delete()
print "document deleted"

fetchedAfterDelete = myBucket.get('bestFlavour')
print "document: ", fetchedAfterDelete.data

