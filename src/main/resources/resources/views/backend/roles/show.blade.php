@extends ('backend.layouts.app')

@section('title') Show Role @endsection

@section('breadcrumbs')
<x-backend-breadcrumbs>
    <x-backend-breadcrumb-item route='{{route("backend.roles.index")}}' icon='c-icon cil-people' >
        Roles
    </x-backend-breadcrumb-item>
    <x-backend-breadcrumb-item type="active">Show</x-backend-breadcrumb-item>
</x-backend-breadcrumbs>
@endsection

@section('content')
<div class="card">
    <div class="card-body">
        <div class="row">
            <div class="col-8">
                <h4 class="card-title mb-0">
                    <i class="c-icon cil-people"></i> {{ __("labels.backend.roles.index.title") }}
                    <small class="text-muted">{{ __("labels.backend.roles.show.action") }} </small>
                </h4>
            </div>
            <div class="col-4">
                <div class="btn-toolbar float-right" role="toolbar" aria-label="Toolbar with button groups">
                    <x-buttons.edit route='{!!route("backend.roles.edit", $role)!!}' class="ml-1" >
                        {{__('Edit')}}
                    </x-buttons.edit>
                </div>
            </div>
        </div>

        <div class="row mt-4">
            <div class="col">
                <div class="table-responsive-sm">
                    <table class="table table-bordered table-hover">
                        <tr>
                            <th>{{ __("labels.backend.roles.fields.name") }}</th>
                            <td>{{ $role->name }}</td>
                        </tr>

                        <tr>
                            <th>{{ __("labels.backend.roles.fields.permissions") }}</th>
                            <td>
                                @if($role->permissions()->count() > 0)
                                    <ul>
                                        @foreach ($role->permissions as $permission)
                                        <li>{{ $permission->name }}</li>
                                        @endforeach
                                    </ul>
                                @endif
                            </td>
                        </tr>

                        <tr>
                            <th>{{ __("labels.backend.roles.fields.created_at") }}</th>
                            <td>{{ $role->created_at }}<br><small>({{ $role->created_at->diffForHumans() }})</small></td>
                        </tr>

                        <tr>
                            <th>{{ __("labels.backend.roles.fields.updated_at") }}</th>
                            <td>{{ $role->updated_at }}<br/><small>({{ $role->updated_at->diffForHumans() }})</small></td>
                        </tr>

                    </table>
                </div><!--table-responsive-->
            </div>
            <!--/.col-->
        </div>
        <!--/.row-->
    </div>
    <div class="card-footer">
        <div class="row">
            <div class="col">
                <small class="float-right text-muted">
                    Updated: {{$role->updated_at->diffForHumans()}},
                    Created at: {{$role->created_at->isoFormat('LLLL')}}
                </small>
            </div>
        </div>
    </div>
</div>
@endsection
