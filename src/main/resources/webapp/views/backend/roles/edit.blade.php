@extends ("backend.layouts.app")

@section('title') Edit Role @endsection

@section('breadcrumbs')
<x-backend-breadcrumbs>
    <x-backend-breadcrumb-item route='{{route("backend.roles.index")}}' icon='c-icon cil-people' >
        Roles
    </x-backend-breadcrumb-item>

    <x-backend-breadcrumb-item type="active">Edit</x-backend-breadcrumb-item>
</x-backend-breadcrumbs>
@endsection

@section("content")
<div class="card">
    <div class="card-body">
        <div class="row">
            <div class="col-8">
                <h4 class="card-title mb-0">
                    <i class="c-icon cil-people"></i> {{ __("labels.backend.roles.edit.title") }}
                    <small class="text-muted">{{ __("labels.backend.roles.edit.action") }} </small>
                </h4>
            </div>
            <div class="col-4">
                <div class="btn-toolbar float-right" role="toolbar" aria-label="Toolbar with button groups">
                    <x-buttons.show route='{!!route("backend.roles.show", $role)!!}' class="ml-1">
                        {{__('Show')}}
                    </x-buttons.show>
                </div>
            </div>
        </div>

        <hr>
        <div class="row mt-4">
            <div class="col">
                {{ html()->modelForm($role, 'PATCH', route("backend.roles.update", $role))->class('form-horizontal')->open() }}

                    <div class="form-group row">
                        {{ html()->label(__("labels.backend.roles.fields.name"))->class('col-md-2 form-control-label')->for('name') }}

                        <div class="col-md-10">
                            {{ html()->text('name')
                                ->class('form-control')
                                ->placeholder(__('labels.backend.roles.fields.name'))
                                ->attribute('maxlength', 191)
                                ->required() }}
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-2">
                            Permissions
                        </div>
                        <div class="col-md-10">
                            @if ($permissions->count())
                                @foreach($permissions as $permission)
                                    <div class="checkbox">
                                        {{ html()->label(html()->checkbox('permissions[]', in_array($permission->name, $role->permissions->pluck('name')->all()), $permission->name)->id('permission-'.$permission->id) . ' ' . $permission->name)->for('permission-'.$permission->id) }}
                                    </div>
                                @endforeach
                            @endif
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-12">
                            <div class="form-group">
                                <x-buttons.edit moduleName="roles"> {{__('Update')}}</x-buttons.edit>
                                @can("delete_roles")
                                <a href="{{route("backend.roles.destroy", $role)}}" class="btn btn-danger" data-method="DELETE" data-token="{{csrf_token()}}" data-toggle="tooltip" title="{{__('labels.backend.delete')}}"><i class="fas fa-trash-alt"></i></a>
                                @endcan
                                <a href="{{ route("backend.roles.index") }}" class="btn btn-warning" data-toggle="tooltip" title="{{__('labels.backend.cancel')}}"><i class="fas fa-reply"></i> Cancel</a>
                            </div>
                        </div>
                    </div>
                {{ html()->form()->close() }}
            </div>

        </div>

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
