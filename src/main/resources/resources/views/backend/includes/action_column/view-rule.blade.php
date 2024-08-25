@can("edit_{str_replace('-', '_', $moduleName)}")
    <a href="{{route("backend.{$moduleName}.rule", $item)}}" class="btn btn-info btn-sm mb-1" data-toggle="tooltip"
       title="{{__('labels.backend.viewRule')}}"><i class="fas fa-rss"></i></a>
@endcan
